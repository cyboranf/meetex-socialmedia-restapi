package com.example.meetexApi.service;

import com.example.meetexApi.model.Groups;
import com.example.meetexApi.repository.GroupsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupsService {
    private final GroupsRepository groupsRepository;

    public GroupsService(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    public Groups save(Groups groups) {
        return groupsRepository.save(groups);
    }

    public void Groups(Groups groups) {
        groupsRepository.delete(groups);
    }
}
