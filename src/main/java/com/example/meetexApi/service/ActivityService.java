package com.example.meetexApi.service;

import com.example.meetexApi.model.Activity;
import com.example.meetexApi.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void delete(Activity activity) {
        activityRepository.delete(activity);
    }

    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }
}
