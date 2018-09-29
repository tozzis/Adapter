package com.sit.cloudnative.PostService.Comment;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void createComment(@Valid Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getByPostId(long id) {
        return commentRepository.findByPostId(id);
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}