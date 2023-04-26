package com.example.meetexApi.service;

import com.example.meetexApi.model.Community;
import com.example.meetexApi.repository.CommunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public void delete(Community community) {
        communityRepository.delete(community);
    }

    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    public Optional<Community> findById(Long id) {
        return communityRepository.findById(id);
    }
}