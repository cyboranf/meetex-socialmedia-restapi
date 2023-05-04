package com.example.meetexApi.repository;

import com.example.meetexApi.model.Comment;
import com.example.meetexApi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

}
