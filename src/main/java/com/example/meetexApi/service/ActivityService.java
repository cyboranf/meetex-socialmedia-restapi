package com.example.meetexApi.service;

import com.example.meetexApi.dto.activity.ActivityResponseDTO;
import com.example.meetexApi.mapper.ActivityMapper;
import com.example.meetexApi.model.Activity;
import com.example.meetexApi.repository.ActivityRepository;
import com.example.meetexApi.validation.ActivityValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ActivityService {
    private final ActivityMapper activityMapper;
    private final ActivityRepository activityRepository;
    private final ActivityValidator activityValidator;

    public ActivityService(ActivityMapper activityMapper, ActivityRepository activityRepository, ActivityValidator activityValidator) {
        this.activityMapper = activityMapper;
        this.activityRepository = activityRepository;
        this.activityValidator = activityValidator;
    }

    public void delete(Long id) {
        activityRepository.delete(activityValidator.deleteValidation(id));
    }

    public ActivityResponseDTO findById(Long id) {
        Activity activity = activityValidator.findActivityValidation(id);

        return activityMapper.activityToActivityResponseDTO(activity);
    }
}
