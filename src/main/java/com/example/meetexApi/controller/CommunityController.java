package com.example.meetexApi.controller;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
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
}
