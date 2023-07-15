package com.example.meetexApi.repository.repository;

import com.example.meetexApi.model.Comment;
import com.example.meetexApi.model.Post;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;
    @Test
    public void whenFindByPost_thenReturnComments() {
        // given
        User testUser = new User();
        Post testPost = new Post();
        Comment testComment = new Comment();

        testComment.setSender(testUser);
        testComment.setPost(testPost);
        testComment.setText("Test Comment");

        entityManager.persist(testUser);
        entityManager.persist(testPost);
        entityManager.persist(testComment);
        entityManager.flush();

        // when
        List<Comment> foundComments = commentRepository.findByPost(testPost);

        // then
        assertThat(foundComments)
                .isNotEmpty()
                .extracting(Comment::getText)
                .contains("Test Comment");
    }
}