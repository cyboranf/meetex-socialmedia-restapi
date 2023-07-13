package com.example.meetexApi.mapper;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.CommunityResponseDTO;
import com.example.meetexApi.model.Community;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommunityMapper {
    CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);

    @Mapping(source = "creator.id", target = "creatorId")
    CommunityResponseDTO communityToCommunityResponseDTO(Community community);

    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "members", ignore = true)
    Community communityRequestDTOtoCommunity(CommunityRequestDTO communityRequestDTO);
}
