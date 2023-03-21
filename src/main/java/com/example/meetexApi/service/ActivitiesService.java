package com.example.meetexApi.service;

import com.example.meetexApi.model.Activities;
import com.example.meetexApi.repository.ActivitiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivitiesService {
    private final ActivitiesRepository activitiesRepository;

    public ActivitiesService(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
    }
    public Activities save(Activities activities) {
        return activitiesRepository.save(activities);
    }
    public void delete(Activities activities) {
        activitiesRepository.delete(activities);
    }
}
