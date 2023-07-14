package com.example.meetexApi.service;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.exception.comment.CommentNotFoundException;
import com.example.meetexApi.exception.post.UnauthorizedException;
import com.example.meetexApi.mapper.CommunityMapper;
import com.example.meetexApi.model.AuthenticatedUser;
import com.example.meetexApi.model.Community;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.CommunityRepository;
import com.example.meetexApi.repository.UserRepository;
import com.example.meetexApi.validation.CommunityValidator;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommunityMapper communityMapper;
    private final CommunityValidator communityValidator;

    public CommunityService(CommunityRepository communityRepository, UserRepository userRepository, CommunityMapper communityMapper, CommunityValidator communityValidator) {
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
        this.communityMapper = communityMapper;
        this.communityValidator = communityValidator;
    }

    /**
     * @param communityRequestDTO
     * @return DTO of new Community
     */
    public CommunityResponseDTO createCommunity(CommunityRequestDTO communityRequestDTO) {
        communityValidator.createCommunityValidation(communityRequestDTO);

        Community community = communityMapper.communityRequestDTOtoCommunity(communityRequestDTO);

        Community savedCommunity = communityRepository.save(community);

        return communityMapper.communityToCommunityResponseDTO(savedCommunity);
    }

    /**
     * @param communityId
     * @return DTO of community with id = id
     */
    public CommunityResponseDTO findById(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()->new CommentNotFoundException("Can not found community with id ="+communityId));
        return communityMapper.communityToCommunityResponseDTO(community);
    }

    /**
     * @param communityId
     * @param communityRequestDTO
     * @return
     */
    public CommunityResponseDTO updateCommunity(Long communityId, CommunityRequestDTO communityRequestDTO) {
        communityValidator.updateCommunityValidation(communityId, communityRequestDTO);

        Community community = communityMapper.communityRequestDTOtoCommunity(communityRequestDTO);

        Community savedCommunity = communityRepository.save(community);

        return communityMapper.communityToCommunityResponseDTO(savedCommunity);
    }

    /**
     * @param communityId
     * @param currentUser
     */
    public void deleteCommunity(Long communityId, User currentUser) {
        communityRepository.delete(communityValidator.deleteCommunity(communityId, currentUser));
    }

    /**
     * @param communityId
     * @param userToAdd
     * @param currentUser
     * @return DTO of community which user was added
     */
    public CommunityResponseDTO addMember(Long communityId, User userToAdd, User currentUser) {
        Community community = communityValidator.addAndRemoveMemberValidation(communityId, userToAdd, currentUser);
        community.getMembers().add(userToAdd);

        Community savedCommunity = communityRepository.save(community);
        return communityMapper.communityToCommunityResponseDTO(savedCommunity);
    }

    /**
     * @param communityId
     * @param userToRemove
     * @param currentUser
     * @return DTO of community which user removed
     */
    public CommunityResponseDTO removeMember(Long communityId, User userToRemove, User currentUser) {
        Community community = communityValidator.addAndRemoveMemberValidation(communityId, userToRemove, currentUser);
        community.getMembers().remove(userToRemove);

        communityRepository.save(community);

        Community savedCommunity = communityRepository.save(community);
        return communityMapper.communityToCommunityResponseDTO(savedCommunity);
    }

    /**
     * @return loggedUser
     */
    private User getCurrentUser() {
        // Retrieve the currently logged-in user from the security context
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authenticatedUser.getUser();
    }
}
