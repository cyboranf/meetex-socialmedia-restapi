package com.example.meetexApi.service;

import com.example.meetexApi.dto.post.PostRequestDTO;
import com.example.meetexApi.dto.post.PostResponseDTO;
import com.example.meetexApi.dto.post.PostUpdateRequest;
import com.example.meetexApi.exception.post.PostNotFoundException;
import com.example.meetexApi.exception.user.UserNotFoundException;
import com.example.meetexApi.mapper.PostMapper;
import com.example.meetexApi.model.Post;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.PostRepository;
import com.example.meetexApi.repository.UserRepository;
import com.example.meetexApi.validation.PostValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostValidator postValidator;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, UserRepository userRepository, PostValidator postValidator, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postValidator = postValidator;
        this.postMapper = postMapper;
    }

    /**
     * @param id method delete post
     */
    public void delete(Long id) {
        postRepository.delete(postValidator.deleteValidation(id));
    }


    /**
     * @param postRequestDTO
     * @param userId
     * @return shared post DTO
     */
    public PostResponseDTO sharePost(PostRequestDTO postRequestDTO, Long userId) {
        postValidator.sharePostValidation(postRequestDTO, userId);

        User creator = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + userId));

        Post post = new Post();

        post.setTitle(postRequestDTO.getTitle());
        post.setText(postRequestDTO.getText());
        post.setSendDate(LocalDateTime.now());
        post.setSender(creator);
        post.setAddressee(creator.getFriends());

        Post savedPost = postRepository.save(post);
        return postMapper.postToPostResponseDTO(savedPost);
    }

    /**
     * @param postId
     * @return
     */
    public PostResponseDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));
        return postMapper.postToPostResponseDTO(post);
    }

    /**
     * @param postId
     * @param postUpdateRequest
     * @return updated post
     */
    public PostResponseDTO updatePost(Long postId, PostUpdateRequest postUpdateRequest) {
        postValidator.updatePostValidation(postId, postUpdateRequest);

        Post post = postRepository.findById(postId).get();

        post.setTitle(postUpdateRequest.getTitle());
        post.setText(postUpdateRequest.getText());

        Post savedPost = postRepository.save(post);
        return postMapper.postToPostResponseDTO(savedPost);
    }

    /**
     * @param postId
     * @param userId
     * @return Dto of Post with new like
     */
    public PostResponseDTO likePost(Long postId, Long userId) {
        User user = postValidator.likingPostValidation(postId, userId);

        Post post = postRepository.findById(postId).get();

        post.getLikes().add(user);
        post.setReactions(post.getReactions() + 1);

        Post savedPost = postRepository.save(post);
        return postMapper.postToPostResponseDTO(savedPost);
    }

    /**
     * @param postId
     * @param userId
     * @return Dto of post without like from user with id = userId
     */
    public PostResponseDTO unlikePost(Long postId, Long userId) {
        User user = postValidator.unlikingPostValidation(postId, userId);

        Post post = postRepository.findById(postId).get();

        post.getLikes().remove(user);
        post.setReactions(post.getReactions() - 1);

        Post savedPost = postRepository.save(post);

        return postMapper.postToPostResponseDTO(savedPost);
    }
}
