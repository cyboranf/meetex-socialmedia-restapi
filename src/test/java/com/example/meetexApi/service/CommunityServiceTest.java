package com.example.meetexApi.service;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.mapper.CommunityMapper;
import com.example.meetexApi.model.Community;
import com.example.meetexApi.repository.CommunityRepository;
import com.example.meetexApi.validation.CommunityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommunityServiceTest {

    @Mock
    private CommunityRepository communityRepository;

    @Mock
    private CommunityMapper communityMapper;

    @Mock
    private CommunityValidator communityValidator;

    @InjectMocks
    private CommunityService communityService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void createCommunityTest() {
        CommunityRequestDTO requestDTO = new CommunityRequestDTO();
        Community community = new Community();
        CommunityResponseDTO responseDTO = new CommunityResponseDTO();

        when(communityMapper.communityRequestDTOtoCommunity(requestDTO)).thenReturn(community);
        when(communityRepository.save(any(Community.class))).thenReturn(community);
        when(communityMapper.communityToCommunityResponseDTO(any(Community.class))).thenReturn(responseDTO);

        CommunityResponseDTO result = communityService.createCommunity(requestDTO);

        assertEquals(responseDTO, result);
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Community community = new Community();
        CommunityResponseDTO responseDTO = new CommunityResponseDTO();

        when(communityRepository.findById(id)).thenReturn(Optional.of(community));
        when(communityMapper.communityToCommunityResponseDTO(any(Community.class))).thenReturn(responseDTO);

        CommunityResponseDTO result = communityService.findById(id);

        assertEquals(responseDTO, result);
    }

    @Test
    public void updateCommunityTest() {
        Long id = 1L;
        CommunityRequestDTO requestDTO = new CommunityRequestDTO();
        Community community = new Community();
        CommunityResponseDTO responseDTO = new CommunityResponseDTO();

        when(communityMapper.communityRequestDTOtoCommunity(requestDTO)).thenReturn(community);
        when(communityRepository.save(any(Community.class))).thenReturn(community);
        when(communityMapper.communityToCommunityResponseDTO(any(Community.class))).thenReturn(responseDTO);

        CommunityResponseDTO result = communityService.updateCommunity(id, requestDTO);

        assertEquals(responseDTO, result);
    }

}
