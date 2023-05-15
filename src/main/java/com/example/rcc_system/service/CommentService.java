package com.example.rcc_system.service;

import com.example.rcc_system.domian.comment.Comment;
import com.example.rcc_system.domian.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository COMMENT_REPOSITORY;

    public void addComment(Comment Comment){
        COMMENT_REPOSITORY.save(Comment);
    }

    public List<Comment> getAllComment() {
        return COMMENT_REPOSITORY.findAll();
    }

    public List<Comment> getBoardCodeComment(int boardCode){
        return COMMENT_REPOSITORY.getBoardCodeComment(boardCode);
    }

    public Comment getCommentById(int commentId) {
        Comment comment = null;
        comment = COMMENT_REPOSITORY.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("없는 댓글")
        );
        return comment;
    }
    @Transactional
    public void updateCommentContext(Comment newComment) {
        Comment comment = getCommentById(newComment.getCommentCode());

        if (comment != null)
            comment.setCommentContext(newComment);
    }

    @Transactional
    public void deleteCommentById(int commentCode) {
        Comment comment = getCommentById(commentCode);

        if (comment != null)
            COMMENT_REPOSITORY.delete(comment);
    }
}
