package com.example.meetexApi.service;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.model.AuthenticatedUser;
import com.example.meetexApi.model.Community;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        return convertToCommunityResponseDTO(savedCommunity);
    }

    private CommunityResponseDTO convertToCommunityResponseDTO(Community community) {
        CommunityResponseDTO communityResponseDTO = new CommunityResponseDTO();
        communityResponseDTO.setId(community.getId());
        communityResponseDTO.setName(community.getName());
        communityResponseDTO.setDescription(community.getDescription());
        communityResponseDTO.setCategory(community.getCategory());
        communityResponseDTO.setCreatorId(community.getCreator().getId());
        communityResponseDTO.setImageUrl(community.getImageUrl());
        return communityResponseDTO;
    }

    private User getCurrentUser() {
        // Retrieve the currently logged-in user from the security context
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authenticatedUser.getUser();
    }
}
