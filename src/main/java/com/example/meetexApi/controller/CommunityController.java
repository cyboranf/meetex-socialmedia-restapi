package com.example.meetexApi.controller;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.dto.community.MemberRequestDTO;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.UserRepository;
import com.example.meetexApi.service.CommunityService;
import com.example.meetexApi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommunityController {

    private final CommunityService communityService;
    private final UserService userService;
    private final UserRepository userRepository;


    public CommunityController(CommunityService communityService, UserService userService, UserRepository userRepository) {
        this.communityService = communityService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * @param communityRequestDTO
     * @return DTO of new community
     */
    @PostMapping("/communities")
    public ResponseEntity<CommunityResponseDTO> createCommunity(@Valid @RequestBody CommunityRequestDTO communityRequestDTO) {
        CommunityResponseDTO createdCommunity = communityService.createCommunity(communityRequestDTO);
        return ResponseEntity.ok().body(createdCommunity);
    }

    /**
     * @param communityId
     * @return DTO of community with id = {communityId}
     */
    @GetMapping("/communities/{communityId}")
    public ResponseEntity<CommunityResponseDTO> getCommunityById(@PathVariable Long communityId) {
        CommunityResponseDTO community = communityService.findById(communityId);
        return ResponseEntity.ok(community);
    }

    /**
     * @param communityId
     * @param communityRequestDTO
     * @return DTO of updated Community
     */
    @PutMapping("/communities/{communityId}")
    public ResponseEntity<CommunityResponseDTO> updateCommunity(
            @PathVariable Long communityId,
            @Valid @RequestBody CommunityRequestDTO communityRequestDTO) {

        CommunityResponseDTO updatedCommunity = communityService.updateCommunity(communityId, communityRequestDTO);
        return ResponseEntity.ok(updatedCommunity);
    }

    /**
     *
     * @param communityId
     * @return
     */
    @DeleteMapping("/communities/{communityId}")
    public ResponseEntity<?> deleteCommunity(@PathVariable Long communityId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User currentUser = userService.getUserByUsername(username);

        communityService.deleteCommunity(communityId, currentUser);
        return ResponseEntity.ok().build();
    }

    /**
     * @param communityId
     * @param memberRequestDTO
     * @return
     */
    @PostMapping("/communities/{communityId}/members")
    public ResponseEntity<?> addMember(@PathVariable Long communityId, @Valid @RequestBody MemberRequestDTO memberRequestDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User currentUser = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        User userToAdd = userRepository.findById(memberRequestDTO.getUserId()).get();

        communityService.addMember(communityId, userToAdd, currentUser);
        return ResponseEntity.ok().build();
    }

    /**
     * @param communityId
     * @param memberId
     * @return
     */
    @DeleteMapping("/communities/{communityId}/members/{memberId}")
    public ResponseEntity<?> removeMember(@PathVariable Long communityId, @PathVariable Long memberId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User userToRemove = userRepository.getById(memberId);
        User currentUser = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        communityService.removeMember(communityId, userToRemove, currentUser);
        return ResponseEntity.ok().build();
    }
}
