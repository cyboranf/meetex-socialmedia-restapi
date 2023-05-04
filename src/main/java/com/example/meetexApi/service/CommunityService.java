package com.example.meetexApi.service;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.exception.ResourceNotFoundException;
import com.example.meetexApi.exception.UnauthorizedException;
import com.example.meetexApi.model.AuthenticatedUser;
import com.example.meetexApi.model.Community;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.CommunityRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommunityService {
    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public Community save(Community community) {
        return communityRepository.save(community);
    }

    public void delete(Community community) {
        communityRepository.delete(community);
    }

    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    public Optional<Community> findById(Long id) {
        return communityRepository.findById(id);
    }

    public CommunityResponseDTO createCommunity(CommunityRequestDTO communityRequestDTO) {
        Community community = new Community();
        community.setName(communityRequestDTO.getName());
        community.setDescription(communityRequestDTO.getDescription());
        community.setCategory(communityRequestDTO.getCategory());
        community.setCreator(getCurrentUser());
        community.setImageUrl(communityRequestDTO.getImageUrl());

        Community savedCommunity = communityRepository.save(community);

        return toCommunityResponseDTO(savedCommunity);
    }

    public Community updateCommunity(Long communityId, CommunityRequestDTO communityRequestDTO, Long creatorId) {
        return findById(communityId)
                .map(community -> {
                    if (community.getCreator().getId().equals(creatorId)) {
                        community.setName(communityRequestDTO.getName());
                        community.setDescription(communityRequestDTO.getDescription());
                        community.setCategory(communityRequestDTO.getCategory());
                        community.setImageUrl(communityRequestDTO.getImageUrl());
                        return save(community);
                    } else {
                        throw new AccessDeniedException("You are not authorized to update this community.");
                    }
                })
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Community not found with ID: " + communityId));
    }

    public CommunityResponseDTO updateCommunity(Long communityId, CommunityRequestDTO communityRequestDTO, User currentUser) {
        Community community = findById(communityId).orElseThrow(() -> new ResourceNotFoundException("Community not found with id: " + communityId));

        if (!community.getCreator().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You are not authorized to update this community");
        }

        community.setName(communityRequestDTO.getName());
        Community updatedCommunity = save(community);

        CommunityResponseDTO responseDTO = new CommunityResponseDTO();
        responseDTO.setId(updatedCommunity.getId());
        responseDTO.setName(updatedCommunity.getName());

        return responseDTO;
    }
    public CommunityResponseDTO toCommunityResponseDTO(Community community) {
        CommunityResponseDTO responseDTO = new CommunityResponseDTO();
        responseDTO.setId(community.getId());
        responseDTO.setName(community.getName());
        responseDTO.setDescription(community.getDescription());
        responseDTO.setCategory(community.getCategory());
        responseDTO.setCreatorId(community.getCreator().getId());
        responseDTO.setImageUrl(community.getImageUrl());
        return responseDTO;
    }

    private User getCurrentUser() {
        // Retrieve the currently logged-in user from the security context
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authenticatedUser.getUser();
    }
}
