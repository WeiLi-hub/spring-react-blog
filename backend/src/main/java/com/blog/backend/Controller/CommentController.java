package com.blog.backend.Controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.backend.Entity.Comment;
import com.blog.backend.Repository.CommentRepository;
import com.blog.backend.Repository.PostRepository;
import com.blog.backend.Service.AuthService;
import com.blog.backend.dto.ContentRequest;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;

    public CommentController(PostRepository postRepository, CommentRepository commentRepository, AuthService authService){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.authService = authService;
    }

    @PostMapping("/comments")
    public ResponseEntity<JsonNode> addComment(@RequestBody ContentRequest contentRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentRepository.save(createNewComment(contentRequest)).asJson());
    }

    private Comment createNewComment(ContentRequest contentRequest){
        return new Comment(
            contentRequest.getContent(),
            LocalDate.now(),
            authService.getBlogUser().orElseThrow(),
            postRepository.getReferenceById(contentRequest.getId())
        );
    }

    @PutMapping("/comments")
    public ResponseEntity<JsonNode> updateComment(@RequestBody ContentRequest contentRequest){
        return commentRepository.findById(contentRequest.getId())
                .map(comment -> comment.updateContent(contentRequest.getContent()))
                .map(commentRepository::save)
                .map(Comment::asJson)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id){
        commentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
