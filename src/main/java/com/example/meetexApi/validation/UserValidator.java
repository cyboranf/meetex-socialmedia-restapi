package com.example.meetexApi.validation;

import com.example.meetexApi.dto.user.UserRegistrationRequest;
import com.example.meetexApi.dto.user.UserRequestDTO;
import com.example.meetexApi.exception.user.*;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.regex.Pattern;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Password pattern explained:
     * ^ - start of the line
     * (?=.*[0-9]) - at least one digit
     * (?=.*[a-z]) - at least one lowercase letter
     * (?=.*[A-Z]) - at least one uppercase letter
     * .{8,} - at least 8 characters
     * $ - end of the line
     */
    private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    /**
     * @param registrationRequest validation of 'registerUser' method
     */
    public void validateRegistration(UserRegistrationRequest registrationRequest) {
        if (registrationRequest.getUsername() == null || userRepository.existsByUserName(registrationRequest.getUsername())) {
            throw new UsernameAlreadyExistsException("Can not register User with username = " + registrationRequest.getUsername() + " because account with the same username is already exist.");
        }
        if (registrationRequest.getEmail() == null || userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Can not register User with email = " + registrationRequest.getEmail() + " because account with the same email is already exists");
        }
        if (!PASSWORD_PATTERN.matcher(registrationRequest.getPassword()).matches()) {
            throw new InvalidPasswordException("Password must be at least 8 characters long, contain at least one digit, " + "one lowercase letter, and one uppercase letter.\"");
        }
    }

    /**
     * @param id
     * @param userRequestDTO validation of 'updateUser' method
     */
    public void validateUpdateOfUser(Long id, UserRequestDTO userRequestDTO) {
        User userToChange = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can not found user with id = " + id));
        if (userRequestDTO.getUserName() == null || (userRepository.existsByUserName(userRequestDTO.getUserName()) && !userRequestDTO.getUserName().equals(userToChange.getUserName()))) {
            throw new UsernameAlreadyExistsException("Can not change username on " + userRequestDTO.getUserName() + " because User with the same username is already exist.");
        }
        if (userRequestDTO.getEmail() == null || (userRepository.existsByEmail(userRequestDTO.getEmail()) && !userRequestDTO.getEmail().equals(userToChange.getEmail()))) {
            throw new EmailAlreadyExistsException("Can not change User email on " + userRequestDTO.getEmail() + " because account with the same email is already exist.");
        }
        if (userRequestDTO.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Your Last name can not be empty.");
        }
    }

    /**
     * @param sender
     * @param receiver
     */
    public void validateFriendRequest(User sender, User receiver) {
        if (sender.equals(receiver)) {
            throw new InvalidFriendRequestException("User can not send a friend request to themselves.");
        }
        if (sender.getFriends().contains(receiver)) {
            throw new InvalidFriendRequestException("User are already friends");
        }
    }

    /**
     * @param userId
     */
    public void validateUserExistsAndHasFriends(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (user.getFriends().isEmpty()) {
            throw new InvalidFriendshipException("User with id: " + userId + " has no friends.");
        }
    }

    /**
     * @param userId
     * @return existingUser or UserNotFoundException
     */
    public User validateUserExists(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    /**
     * @param user
     * @param sender
     */
    public void validateFriendRequestReceived(User user, User sender) {
        if (!user.getReceivedFriendRequests().contains(sender)) {
            throw new FriendRequestNotFoundException("Invalid friend request.");
        }
    }

    /**
     * @param sender
     * @param receiver
     */
    public void validateFriendRequestSent(User sender, User receiver) {
        if (!sender.getSentFriendRequests().contains(receiver)) {
            throw new FriendRequestNotFoundException("No friend request found from user with id: " + sender.getId() + " to user with id: " + receiver.getId());
        }
    }
}
