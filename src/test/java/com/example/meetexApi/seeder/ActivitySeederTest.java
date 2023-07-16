package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Activity;
import com.example.meetexApi.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ActivitySeederTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivitySeeder activitySeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreateActivity_whenActivityRepositoryIsEmpty() throws Exception {
        when(activityRepository.count()).thenReturn(0L);

        activitySeeder.run();

        verify(activityRepository, times(1)).save(any(Activity.class));
    }

    @Test
    public void run_shouldNotCreateActivity_whenActivityRepositoryIsNotEmpty() throws Exception {
        when(activityRepository.count()).thenReturn(1L);

        activitySeeder.run();

        verify(activityRepository, times(0)).save(any(Activity.class));
    }
}
