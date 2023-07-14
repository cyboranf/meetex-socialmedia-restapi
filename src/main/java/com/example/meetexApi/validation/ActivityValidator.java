package com.example.meetexApi.validation;

import com.example.meetexApi.exception.activity.ActivityNotFoundException;
import com.example.meetexApi.model.Activity;
import com.example.meetexApi.repository.ActivityRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivityValidator {
    private final ActivityRepository activityRepository;

    public ActivityValidator(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity deleteValidation(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException("Can not delete activity with id = " + id));
    }

    public Activity findActivityValidation(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(()->new ActivityNotFoundException("Can not found activity with id = "+id));
    }
}
