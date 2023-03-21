package com.example.meetexApi.service;

import com.example.meetexApi.model.Community;
import com.example.meetexApi.repository.CommunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommunityService {
    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public Community save(Community community) {
        return communityRepository.save(community);
    }

    public void Groups(Community community) {
        communityRepository.delete(community);
    }
}
