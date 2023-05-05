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

    @PostMapping("/communities")
    public ResponseEntity<CommunityResponseDTO> createCommunity(@Valid @RequestBody CommunityRequestDTO communityRequestDTO) {
        CommunityResponseDTO createdCommunity = communityService.createCommunity(communityRequestDTO);
        return ResponseEntity.ok().body(createdCommunity);
    }

    @GetMapping("/communities/{communityId}")
    public ResponseEntity<CommunityResponseDTO> getCommunityById(@PathVariable Long communityId) {
        return communityService.findById(communityId)
                .map(community -> ResponseEntity.ok(communityService.toCommunityResponseDTO(community)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/communities/{communityId}")
    public ResponseEntity<CommunityResponseDTO> updateCommunity(
            @PathVariable Long communityId,
            @Valid @RequestBody CommunityRequestDTO communityRequestDTO) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User currentUser = userService.findUserByUserName(username);

        CommunityResponseDTO updatedCommunity = communityService.updateCommunity(communityId, communityRequestDTO, currentUser);
        return ResponseEntity.ok(updatedCommunity);
    }

    @DeleteMapping("/communities/{communityId}")
    public ResponseEntity<?> deleteCommunity(@PathVariable Long communityId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User currentUser = userService.findUserByUserName(username);

        communityService.deleteCommunity(communityId, currentUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/communities/{communityId}/members")
    public ResponseEntity<?> addMember(@PathVariable Long communityId, @Valid @RequestBody MemberRequestDTO memberRequestDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User currentUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        User userToAdd = userRepository.findById(memberRequestDTO.getUserId()).get();

        communityService.addMember(communityId, userToAdd, currentUser);
        return ResponseEntity.ok().build();
    }
}
