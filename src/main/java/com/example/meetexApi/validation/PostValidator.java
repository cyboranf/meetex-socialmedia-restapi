package com.example.meetexApi.validation;

import com.example.meetexApi.dto.post.PostRequestDTO;
import com.example.meetexApi.dto.post.PostUpdateRequest;
import com.example.meetexApi.exception.post.InvalidPostException;
import com.example.meetexApi.exception.post.PostNotFoundException;
import com.example.meetexApi.exception.post.UserHasLikedPostException;
import com.example.meetexApi.exception.post.UserHasNotLikedPostException;
import com.example.meetexApi.exception.user.UserNotFoundException;
import com.example.meetexApi.model.Post;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.PostRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PostValidator {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostValidator(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param postId
     * @return validated post or PostNotFoundException
     */
    public Post deleteValidation(Long postId) {
        Post returnedPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Can not found post with id =" + postId));
        return returnedPost;
    }

    /**
     * @param postRequestDTO
     * @param userId
     * @return validated post or InvalidPostException
     */
    public void sharePostValidation(PostRequestDTO postRequestDTO, Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Can not found User with id =" + userId));
        if (postRequestDTO.getTitle() == null || postRequestDTO.getTitle().length() > 100) {
            throw new InvalidPostException("Post title can not be null and can not have more than 100 characters length.");
        }
        if (postRequestDTO.getText() == null || postRequestDTO.getText().length() > 1000) {
            throw new InvalidPostException("Post text can not be null and can not have more than 1000 characters length.");
        }
    }

    /**
     * @param postId
     * @param postUpdateRequest
     * @return validated post or InvalidPostException
     */
    public void updatePostValidation(Long postId, PostUpdateRequest postUpdateRequest) {
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Can not found post with id =" + postId));
        if (postUpdateRequest.getTitle() == null || postUpdateRequest.getTitle().length() > 100) {
            throw new InvalidPostException("Post title can not be null and can not have more than 100 characters length.");
        }
        if (postUpdateRequest.getText() == null || postUpdateRequest.getText().length() > 1000) {
            throw new InvalidPostException("Post text can not be null and can not have more than 1000 characters length.");
        }
    }

    /**
     * @param postId
     * @param userId
     * @return User which is adding like
     */
    public User likingPostValidation(Long postId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Can not found post with id = " + postId));
        if (post.getLikes().contains(user)) {
            throw new UserHasLikedPostException("User already like this post.");
        }
        return user;
    }

    /**
     * @param postId
     * @param userId
     * @return User which is adding like
     */
    public User unlikingPostValidation(Long postId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Can not found post with id = " + postId));
        if (!post.getLikes().contains(user)) {
            throw new UserHasNotLikedPostException("User already like this post.");
        }
        return user;
    }
}
