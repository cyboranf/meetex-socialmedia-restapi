package com.example.meetexApi.repository;

import com.example.meetexApi.model.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<Activities, Integer> {
}
