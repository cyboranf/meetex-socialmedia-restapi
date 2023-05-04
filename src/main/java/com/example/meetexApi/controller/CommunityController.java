package com.example.meetexApi.controller;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.model.AuthenticatedUser;
import com.example.meetexApi.model.Community;
import com.example.meetexApi.model.User;
import com.example.meetexApi.service.CommunityService;
import com.example.meetexApi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommunityController {

    private final CommunityService communityService;
    private final UserService userService;

    public CommunityController(CommunityService communityService, UserService userService) {
        this.communityService = communityService;
        this.userService = userService;
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

}
