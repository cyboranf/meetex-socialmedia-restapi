package com.example.meetexApi.service;

import com.example.meetexApi.dto.friendRequest.FriendRequestDTO;
import com.example.meetexApi.dto.user.UserRegistrationRequest;
import com.example.meetexApi.dto.user.UserRequestDTO;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.mapper.UserMapper;
import com.example.meetexApi.model.Role;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.RoleRepository;
import com.example.meetexApi.repository.UserRepository;
import com.example.meetexApi.validation.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserValidator userValidator;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    private UserRegistrationRequest registrationRequest;
    private UserResponseDTO expectedUserResponseDTO;
    private User user;
    private Role role;

    @BeforeEach
    public void setUp() {
        // Setup your objects here
        registrationRequest = new UserRegistrationRequest();
        registrationRequest.setUsername("username");
        registrationRequest.setEmail("email@email.com");
        registrationRequest.setPassword("password");

        user = new User();
        user.setUserName(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());

        role = new Role();
        role.setName("ROLE_USER");

        expectedUserResponseDTO = new UserResponseDTO();
        expectedUserResponseDTO.setUserName("username");
        expectedUserResponseDTO.setEmail("email@email.com");
    }

    @Test
    void registerUser() {

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));
        when(userRepository.save(any())).thenReturn(user);
        when(userMapper.userToUserResponseDTO(any())).thenReturn(expectedUserResponseDTO);

        UserResponseDTO actualUserResponseDTO = userService.registerUser(registrationRequest);

        assertEquals(expectedUserResponseDTO, actualUserResponseDTO);
    }

    @Test
    void findById() {
        Long id = 1L;
        User user = new User();
        user.setUserName("username");
        user.setEmail("email@email.com");

        UserResponseDTO expectedUserResponseDTO = new UserResponseDTO();
        expectedUserResponseDTO.setUserName("username");
        expectedUserResponseDTO.setEmail("email@email.com");

        // When
        when(userValidator.validateUserExists(any())).thenReturn(user);
        when(userMapper.userToUserResponseDTO(any())).thenReturn(expectedUserResponseDTO);

        // Then
        UserResponseDTO actualUserResponseDTO = userService.findById(id);
        assertEquals(expectedUserResponseDTO, actualUserResponseDTO);
    }

    @Test
    void updateUser() {
        Long id = 1L;
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUserName("newUsername");
        userRequestDTO.setLastName("newLastName");
        userRequestDTO.setEmail("newEmail@email.com");

        User user = new User();
        user.setUserName("username");
        user.setEmail("email@email.com");

        User savedUser = new User();
        savedUser.setUserName(userRequestDTO.getUserName());
        savedUser.setLastName(userRequestDTO.getLastName());
        savedUser.setEmail(userRequestDTO.getEmail());

        UserResponseDTO expectedUserResponseDTO = new UserResponseDTO();
        expectedUserResponseDTO.setUserName(userRequestDTO.getUserName());
        expectedUserResponseDTO.setLastName(userRequestDTO.getLastName());
        expectedUserResponseDTO.setEmail(userRequestDTO.getEmail());

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userMapper.userToUserResponseDTO(any())).thenReturn(expectedUserResponseDTO);

        UserResponseDTO actualUserResponseDTO = userService.updateUser(id, userRequestDTO);
        assertEquals(expectedUserResponseDTO, actualUserResponseDTO);
    }

    @Test
    void sendFriendRequest() {
        Long userId = 1L;
        FriendRequestDTO friendRequestDTO = new FriendRequestDTO();
        friendRequestDTO.setSenderId(1L);
        friendRequestDTO.setReceiverId(2L);

        User sender = new User();
        sender.setUserName("senderUsername");
        sender.setId(friendRequestDTO.getSenderId());
        sender.setSentFriendRequests(new ArrayList<>());

        User receiver = new User();
        receiver.setUserName("receiverUsername");
        receiver.setId(friendRequestDTO.getReceiverId());
        receiver.setReceivedFriendRequests(new ArrayList<>());

        String expectedResponse = "Friend request sent to user with username = " + receiver.getUserName();

        when(userRepository.findById(friendRequestDTO.getSenderId())).thenReturn(Optional.of(sender));
        when(userRepository.findById(friendRequestDTO.getReceiverId())).thenReturn(Optional.of(receiver));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);  //returns the same user as saved

        String actualResponse = userService.sendFriendRequest(userId, friendRequestDTO);
        assertEquals(expectedResponse, actualResponse);

        verify(userValidator).validateFriendRequest(sender, receiver);
        verify(userRepository, times(2)).save(any(User.class));
    }

    @Test
    void getAllFriends() {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        User friend1 = new User();
        friend1.setId(2L);
        User friend2 = new User();
        friend2.setId(3L);
        user.setFriends(Arrays.asList(friend1, friend2));

        UserResponseDTO friendResponse1 = new UserResponseDTO();
        friendResponse1.setId(friend1.getId());
        UserResponseDTO friendResponse2 = new UserResponseDTO();
        friendResponse2.setId(friend2.getId());

        List<UserResponseDTO> expectedList = Arrays.asList(friendResponse1, friendResponse2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.userToUserResponseDTO(friend1)).thenReturn(friendResponse1);
        when(userMapper.userToUserResponseDTO(friend2)).thenReturn(friendResponse2);

        List<UserResponseDTO> actualList = userService.getAllFriends(userId);

        assertEquals(expectedList.size(), actualList.size());
        assertTrue(actualList.containsAll(expectedList));

        verify(userValidator).validateUserExistsAndHasFriends(userId);
    }

    @Test
    void declineFriendRequest() {
        Long userId = 1L;
        Long senderId = 2L;

        User user = new User();
        user.setId(userId);

        User sender = new User();
        sender.setId(senderId);

        user.setReceivedFriendRequests(new ArrayList<>(Collections.singletonList(sender)));
        sender.setSentFriendRequests(new ArrayList<>(Collections.singletonList(user)));

        when(userValidator.validateUserExists(userId)).thenReturn(user);
        when(userValidator.validateUserExists(senderId)).thenReturn(sender);

        userService.declineFriendRequest(userId, senderId);

        assertTrue(user.getReceivedFriendRequests().isEmpty());
        assertTrue(sender.getSentFriendRequests().isEmpty());

        verify(userRepository).save(user);
        verify(userRepository).save(sender);

        verify(userValidator).validateUserExists(userId);
        verify(userValidator).validateUserExists(senderId);
        verify(userValidator).validateFriendRequestReceived(user, sender);
    }

    @Test
    void deleteFriendRequest() {
        Long userId = 1L;
        Long receiverId = 2L;

        User sender = new User();
        sender.setId(userId);

        User receiver = new User();
        receiver.setId(receiverId);

        sender.setSentFriendRequests(new ArrayList<>(Collections.singletonList(receiver)));
        receiver.setReceivedFriendRequests(new ArrayList<>(Collections.singletonList(sender)));

        when(userValidator.validateUserExists(userId)).thenReturn(sender);
        when(userValidator.validateUserExists(receiverId)).thenReturn(receiver);

        userService.deleteFriendRequest(userId, receiverId);

        assertTrue(sender.getSentFriendRequests().isEmpty());
        assertTrue(receiver.getReceivedFriendRequests().isEmpty());

        verify(userRepository).save(sender);
        verify(userRepository).save(receiver);

        verify(userValidator).validateUserExists(userId);
        verify(userValidator).validateUserExists(receiverId);
        verify(userValidator).validateFriendRequestSent(sender, receiver);
    }
}