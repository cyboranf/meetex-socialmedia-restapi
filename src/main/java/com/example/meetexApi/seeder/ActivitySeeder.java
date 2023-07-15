package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Activity;
import com.example.meetexApi.repository.ActivityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@Profile("seed")
public class ActivitySeeder implements DatabaseSeeder {
    private final ActivityRepository activityRepository;

    public ActivitySeeder(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * Activity:
     * "1": {
     * "name": "Hiking",
     * "description": "This is the first Activity in our web app.",
     * "category": "Unknown",
     * "imageUrl": "https://meetex.com/activity.jpg"
     * }
     */

    @Override
    public void run(String... args) throws Exception {
        if (activityRepository.count() == 0) {
            Activity activity = new Activity();
            activity.setName("Hiking");
            activity.setDescription("This is the first Activity in our web app.");
            activity.setCategory("Unknown");
            activity.setImageUrl("https://meetex.com/activity.jpg");

            activityRepository.save(activity);
        }
    }
}
