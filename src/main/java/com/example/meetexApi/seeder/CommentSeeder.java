package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Comment;
import com.example.meetexApi.repository.CommentRepository;
import com.example.meetexApi.repository.PostRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(9)
@Profile("seed")
public class CommentSeeder implements DatabaseSeeder {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentSeeder(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /**
     * Comment:
     * "1": {
     * "text": "This is a sample comment"
     * }
     */

    @Override
    public void run(String... args) throws Exception {
        if (commentRepository.count() == 0) {
            Comment comment = new Comment();
            comment.setText("This is a sample comment");
            comment.setSendDate(LocalDateTime.now());

            commentRepository.save(comment);
        }
    }
}
