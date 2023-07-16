package com.example.meetexApi.controller;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.dto.community.MemberRequestDTO;
import com.example.meetexApi.model.User;
import com.example.meetexApi.service.CommunityService;
import com.example.meetexApi.service.UserService;
import com.example.meetexApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommunityControllerTest {

    @Mock
    private CommunityService communityService;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommunityController communityController;

    private CommunityRequestDTO communityRequestDTO;
    private CommunityResponseDTO communityResponseDTO;
    private MemberRequestDTO memberRequestDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        communityRequestDTO = new CommunityRequestDTO();
        communityResponseDTO = new CommunityResponseDTO();
        memberRequestDTO = new MemberRequestDTO();
        memberRequestDTO.setUserId(1L);
        user = new User();
        user.setUserName("testuser");

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername("testuser").password("password").authorities("ROLE_USER").build();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
    }

    @Test
    public void createCommunityTest() {
        when(communityService.createCommunity(any(CommunityRequestDTO.class))).thenReturn(communityResponseDTO);

        ResponseEntity<CommunityResponseDTO> result = communityController.createCommunity(communityRequestDTO);

        assertEquals(communityResponseDTO, result.getBody());
    }

    @Test
    public void getCommunityByIdTest() {
        when(communityService.findById(anyLong())).thenReturn(communityResponseDTO);

        ResponseEntity<CommunityResponseDTO> result = communityController.getCommunityById(1L);

        assertEquals(communityResponseDTO, result.getBody());
    }

    @Test
    public void updateCommunityTest() {
        when(communityService.updateCommunity(anyLong(), any(CommunityRequestDTO.class))).thenReturn(communityResponseDTO);

        ResponseEntity<CommunityResponseDTO> result = communityController.updateCommunity(1L, communityRequestDTO);

        assertEquals(communityResponseDTO, result.getBody());
    }

    @Test
    public void deleteCommunityTest() {
        when(userService.getUserByUsername(anyString())).thenReturn(user);
        doNothing().when(communityService).deleteCommunity(anyLong(), any(User.class));

        ResponseEntity<?> result = communityController.deleteCommunity(1L);

        assertEquals(ResponseEntity.ok().build(), result);
    }

    @Test
    public void addMemberTest() {
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(communityService).addMember(anyLong(), any(User.class), any(User.class));

        ResponseEntity<?> result = communityController.addMember(1L, memberRequestDTO);

        assertEquals(ResponseEntity.ok().build(), result);
    }

    @Test
    public void removeMemberTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
        doNothing().when(communityService).removeMember(anyLong(), any(User.class), any(User.class));

        ResponseEntity<?> result = communityController.removeMember(1L, 1L);

        assertEquals(ResponseEntity.ok().build(), result);
    }
}
