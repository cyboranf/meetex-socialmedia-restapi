package com.example.meetexApi.seed;

import com.example.meetexApi.model.*;
import com.example.meetexApi.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final CommunityRepository communityRepository;
    private final CompanyPageRepository companyPageRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MessageRepository messageRepository;
    private final NotificationRepository notificationRepository;
    private final RoleRepository roleRepository;

    public DataLoader(UserRepository userRepository, ActivityRepository activityRepository,
                      CommunityRepository communityRepository, CompanyPageRepository companyPageRepository,
                      PostRepository postRepository, CommentRepository commentRepository,
                      MessageRepository messageRepository, NotificationRepository notificationRepository,
                      RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.communityRepository = communityRepository;
        this.companyPageRepository = companyPageRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.messageRepository = messageRepository;
        this.notificationRepository = notificationRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setEmail("john.doe@example.com");
        user1.setUserName("JohnDoe");
        user1.setLastName("Doe");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setEmail("jane.smith@example.com");
        user2.setUserName("JaneSmith");
        user2.setLastName("Smith");
        user2.setPassword("password2");

        User user3 = new User();
        user3.setEmail("susan.brown@example.com");
        user3.setUserName("SusanBrown");
        user3.setLastName("Brown");
        user3.setPassword("password3");

        User user4 = new User();
        user4.setEmail("michael.johnson@example.com");
        user4.setUserName("MichaelJohnson");
        user4.setLastName("Johnson");
        user4.setPassword("password4");

        User user5 = new User();
        user5.setEmail("kevin.wilson@example.com");
        user5.setUserName("KevinWilson");
        user5.setLastName("Wilson");
        user5.setPassword("password5");

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        Activity activity1 = new Activity();
        activity1.setName("Hiking");

        Activity activity2 = new Activity();
        activity2.setName("Travelling");

        Activity activity3 = new Activity();
        activity3.setName("Cooking");

        Activity activity4 = new Activity();
        activity4.setName("Photography");

        Activity activity5 = new Activity();
        activity5.setName("Dancing");

        activityRepository.saveAll(Arrays.asList(activity1, activity2, activity3, activity4, activity5));

        Community community1 = new Community();
        community1.setName("Nature Lovers");

        Community community2 = new Community();
        community2.setName("Foodies United");

        Community community3 = new Community();
        community3.setName("Photography Enthusiasts");

        communityRepository.saveAll(Arrays.asList(community1, community2, community3));

        CompanyPage companyPage1 = new CompanyPage();
        companyPage1.setName("Tech Corp");
        companyPage1.setDescription("Tech Corp is a leading technology company.");


        CompanyPage companyPage2 = new CompanyPage();
        companyPage2.setName("Travel Unlimited");
        companyPage2.setDescription("Travel Unlimited offers the best travel experiences.");
        companyPageRepository.saveAll(Arrays.asList(companyPage1, companyPage2));

        Post post1 = new Post();
        postRepository.save(post1);

        Comment comment1 = new Comment();
        comment1.setPost(post1);
        commentRepository.save(comment1);

        Message message1 = new Message();
        message1.setSender(user1);
        message1.setRecipient(user2);


        messageRepository.save(message1);

        Notification notification1 = new Notification();


        notificationRepository.save(notification1);

        Role role1 = new Role();
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        roleRepository.saveAll(Arrays.asList(role1, role2));

        user1.setRoles(new HashSet<>(Arrays.asList(role1)));
        user2.setRoles(new HashSet<>(Arrays.asList(role1)));
        user3.setRoles(new HashSet<>(Arrays.asList(role1)));
        user4.setRoles(new HashSet<>(Arrays.asList(role1)));
        user5.setRoles(new HashSet<>(Arrays.asList(role1)));

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}
