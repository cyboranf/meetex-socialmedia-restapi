package com.example.meetexApi.mapper;

import com.example.meetexApi.dto.activity.ActivityRequestDTO;
import com.example.meetexApi.dto.activity.ActivityResponseDTO;
import com.example.meetexApi.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    ActivityResponseDTO activityToActivityResponseDTO(Activity activity);

    @Mapping(target = "activities_id", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "iamgeUrl", ignore = true)
    @Mapping(target = "users", ignore = true)
    Activity activityRequestDTOtoActivity(ActivityRequestDTO activityRequestDTO);
}
