package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Community;
import com.example.meetexApi.repository.CommunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommunitySeederTest {

    @Mock
    private CommunityRepository communityRepository;

    @InjectMocks
    private CommunitySeeder communitySeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreateCommunity_whenCommunityRepositoryIsEmpty() throws Exception {
        when(communityRepository.count()).thenReturn(0L);

        communitySeeder.run();

        verify(communityRepository, times(1)).save(any(Community.class));
    }

    @Test
    public void run_shouldNotCreateCommunity_whenCommunityRepositoryIsNotEmpty() throws Exception {
        when(communityRepository.count()).thenReturn(1L);

        communitySeeder.run();

        verify(communityRepository, times(0)).save(any(Community.class));
    }
}
