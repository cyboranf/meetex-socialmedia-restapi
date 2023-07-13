package com.example.meetexApi.validation;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.exception.community.*;
import com.example.meetexApi.exception.post.UnauthorizedException;
import com.example.meetexApi.model.Community;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.CommunityRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CommunityValidator {
    private final CommunityRepository communityRepository;

    private final UserRepository userRepository;

    public CommunityValidator(CommunityRepository communityRepository, UserRepository userRepository) {
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param communityRequestDTO
     */
    public void createCommunityValidation(CommunityRequestDTO communityRequestDTO) {
        if (communityRequestDTO.getName() == null || communityRequestDTO.getName().length() > 100) {
            throw new InvalidCommunityNameException("Name of community can not be null and can not have more than 100 characters.");
        }
        if (communityRequestDTO.getDescription() == null || communityRequestDTO.getDescription().length() > 500) {
            throw new InvalidCommunityDescriptionException("Description of community can not be null and can not have more than 500 characters.");
        }
        if (communityRequestDTO.getCategory() == null || communityRequestDTO.getCategory().length() > 20) {
            throw new InvalidCommunityCategory("Category of community can not be null and cannot have more than 20 characters.");
        }
        if (communityRequestDTO.getImageUrl() == null) {
            communityRequestDTO.setImageUrl("no-content");
        }
    }

    /**
     * @param communityId
     * @param communityRequestDTO
     * @return validated Community
     */
    public void updateCommunityValidation(Long communityId, CommunityRequestDTO communityRequestDTO) {
        Community updatingCommunity = communityRepository.findById(communityId)
                .orElseThrow(() -> new CommunityNotFoundException("Can not found community with id = " + communityId));

        createCommunityValidation(communityRequestDTO);
    }

    /**
     * @param communityId
     * @param currentUser
     * @return exception or Community that User want delete
     */
    public Community deleteCommunity(Long communityId, User currentUser) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new CommunityNotFoundException("Can not found community with id = " + communityId));
        if (!community.getCreator().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You are not authorized to delete this community");
        }
        return community;
    }

    /**
     * @param communityId
     * @param userToAdd
     * @param currentUser
     * @return exception or community which somebody want to add or remove user
     */
    public Community addAndRemoveMemberValidation(Long communityId, User userToAdd, User currentUser) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new CommunityNotFoundException("Can not found community with id = " + communityId));
        if (community.getMembers().contains(userToAdd)) {
            throw new InvalidUserToAddException("Can not add user to community, because user is already in.");
        }
        if (!community.getCreator().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You are not authorized to add members to this community");
        }
        return community;
    }
}
