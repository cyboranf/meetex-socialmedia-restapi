package com.example.meetexApi.service;

import com.example.meetexApi.dto.activity.ActivityResponseDTO;
import com.example.meetexApi.mapper.ActivityMapper;
import com.example.meetexApi.model.Activity;
import com.example.meetexApi.repository.ActivityRepository;
import com.example.meetexApi.validation.ActivityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    @Mock
    private ActivityMapper activityMapper;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ActivityValidator activityValidator;

    @InjectMocks
    private ActivityService activityService;

    @BeforeEach
    public void setUp() {
        // Intentionally left blank
    }

    @Test
    public void deleteActivityTest() {
        Long id = 1L;
        Activity activity = new Activity();

        when(activityValidator.deleteValidation(any(Long.class))).thenReturn(activity);

        activityService.delete(id);

        verify(activityRepository, times(1)).delete(activity);
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Activity activity = new Activity();
        ActivityResponseDTO responseDTO = new ActivityResponseDTO();

        when(activityValidator.findActivityValidation(any(Long.class))).thenReturn(activity);
        when(activityMapper.activityToActivityResponseDTO(activity)).thenReturn(responseDTO);

        ActivityResponseDTO result = activityService.findById(id);

        assertEquals(responseDTO, result);
    }
}
