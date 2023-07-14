package com.example.meetexApi.service;

import com.example.meetexApi.dto.friendRequest.FriendRequestDTO;
import com.example.meetexApi.dto.user.UserRegistrationRequest;
import com.example.meetexApi.dto.user.UserRequestDTO;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.exception.user.RoleNotFoundException;
import com.example.meetexApi.exception.user.UserNotFoundException;
import com.example.meetexApi.mapper.UserMapper;
import com.example.meetexApi.model.Message;
import com.example.meetexApi.model.Role;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.RoleRepository;
import com.example.meetexApi.repository.UserRepository;
import com.example.meetexApi.validation.UserValidator;

import com.google.api.services.gmail.Gmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    @Autowired
    private Gmail gmailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserValidator userValidator, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
    }

    /**
     * @param registrationRequest
     * @return DTO of new User
     */
    public UserResponseDTO registerUser(UserRegistrationRequest registrationRequest) {
        userValidator.validateRegistration(registrationRequest);

        User newUser = new User();
        newUser.setUserName(registrationRequest.getUsername());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // Assign a default role to the user
        Role defaultRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RoleNotFoundException("Default user role not found."));
        newUser.setRoles(Collections.singleton(defaultRole));

        User savedUser = userRepository.save(newUser);

//        javax.mail.Message welcomeMessage = createWelcomeMessage(savedUser.getEmail(), savedUser.getUserName());
//        gmailService.users().messages().send("me", welcomeMessage).execute();


        return userMapper.userToUserResponseDTO(savedUser);
    }

    public UserResponseDTO findById(Long id) {
        return userMapper.userToUserResponseDTO(userValidator.validateUserExists(id));
    }

    /**
     * @param id
     * @param userRequestDTO
     * @return Dto of updatedUser
     */
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        userValidator.validateUpdateOfUser(id, userRequestDTO);

        User updatingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        updatingUser.setUserName(userRequestDTO.getUserName());
        updatingUser.setLastName(userRequestDTO.getLastName());
        updatingUser.setEmail(userRequestDTO.getEmail());

        User savedUser = userRepository.save(updatingUser);

        return userMapper.userToUserResponseDTO(savedUser);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUserName(username);
    }

    /**
     * @param userId
     * @param friendRequestDTO
     * @return communicat about friend request
     */
    public String sendFriendRequest(Long userId, FriendRequestDTO friendRequestDTO) {
        User sender = userRepository.findById(friendRequestDTO.getSenderId()).orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + friendRequestDTO.getSenderId()));
        User receiver = userRepository.findById(friendRequestDTO.getReceiverId()).orElseThrow(() -> new UserNotFoundException("Can not found User with id = " + friendRequestDTO.getReceiverId()));

        userValidator.validateFriendRequest(sender, receiver);

        sender.getSentFriendRequests().add(receiver);
        receiver.getReceivedFriendRequests().add(sender);

        userRepository.save(sender);
        userRepository.save(receiver);

        return "Friend request sent to user with username = " + receiver.getUserName();
    }

    /**
     * @param userId
     * @return List of all user friends
     */
    public List<UserResponseDTO> getAllFriends(Long userId) {
        userValidator.validateUserExistsAndHasFriends(userId);

        User user = userRepository.findById(userId).get();  // We can call .get() safely now because we've validated that the user exists.

        List<UserResponseDTO> returnedList = user.getFriends().stream().map(userMapper::userToUserResponseDTO).collect(Collectors.toList());

        return returnedList;
    }

    /**
     * @param userId
     * @param senderId
     */
    public void acceptFriendRequest(Long userId, Long senderId) {
        User user = userValidator.validateUserExists(userId);
        User sender = userValidator.validateUserExists(senderId);

        userValidator.validateFriendRequestReceived(user, sender);

        // Add sender to user's friends list and vice versa
        user.getFriends().add(sender);
        sender.getFriends().add(user);

        // Remove sender from user's received friend requests and vice versa
        user.getReceivedFriendRequests().remove(sender);
        sender.getSentFriendRequests().remove(user);

        userRepository.save(user);
        userRepository.save(sender);
    }

    /**
     * @param userId
     * @param senderId
     */
    public void declineFriendRequest(Long userId, Long senderId) {
        User user = userValidator.validateUserExists(userId);
        User sender = userValidator.validateUserExists(senderId);

        userValidator.validateFriendRequestReceived(user, sender);

        // Remove sender from user's received friend requests and vice versa
        user.getReceivedFriendRequests().remove(sender);
        sender.getSentFriendRequests().remove(user);

        userRepository.save(user);
        userRepository.save(sender);
    }

    /**
     * @param userId
     * @param receiverId
     */
    public void deleteFriendRequest(Long userId, Long receiverId) {
        User sender = userValidator.validateUserExists(userId);
        User receiver = userValidator.validateUserExists(receiverId);

        userValidator.validateFriendRequestSent(sender, receiver);

        // Remove receiver from sender's sent friend requests and vice versa
        sender.getSentFriendRequests().remove(receiver);
        receiver.getReceivedFriendRequests().remove(sender);

        userRepository.save(sender);
        userRepository.save(receiver);
    }

    private Message createWelcomeMessage(String toEmail, String userName) throws MessagingException, IOException {
        MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
        mimeMessage.setFrom(new InternetAddress("meetex@gmail.com"));  //fake
        mimeMessage.setSubject("Welcome to our service!");
        mimeMessage.setText("Hi " + userName + ",\n\n" +
                "Welcome to our service! We are glad to have you on board. " +
                "You have successfully created a new account. Please let us know if you have any questions.\n\n" +
                "Best,\n" +
                "The Team");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mimeMessage.writeTo(outputStream);
        byte[] bytes = outputStream.toByteArray();
        String encodedEmail = Base64.getUrlEncoder().encodeToString(bytes);
        Message message = new Message();
        message.setText(encodedEmail);

        return message;
    }
}
