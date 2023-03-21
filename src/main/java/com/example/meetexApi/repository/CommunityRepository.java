package com.example.meetexApi.repository;

import com.example.meetexApi.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {

}
