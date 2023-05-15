package com.example.rcc_system.controller;

import com.example.rcc_system.domian.comment.Comment;
import com.example.rcc_system.domian.comment.CommentRequestDto;
import com.example.rcc_system.service.CommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService COMMENT_SERVICE;
    @PostMapping("addComment")
    public void addNewComment(@RequestBody CommentRequestDto commentRequestDto){
        Comment comment = new Comment(commentRequestDto);
        COMMENT_SERVICE.addComment(comment);
    }

    @GetMapping("getAllComment")
    public List<Comment> commentList(){
        return COMMENT_SERVICE.getAllComment();
    }

    @GetMapping("getCommentBoardCodeList")
    public List<Comment> boardCommentList(@RequestParam int boardCode){
        return COMMENT_SERVICE.getBoardCodeComment(boardCode);
    }

    @PutMapping("updateComment")
    public void updateComment(@RequestBody CommentRequestDto commentRequestDto){
        Comment comment = new Comment(commentRequestDto);
        COMMENT_SERVICE.updateCommentContext(comment);
    }

    @DeleteMapping("deleteComment")
    public void deleteComment(@RequestParam int commentCode) {
        COMMENT_SERVICE.deleteCommentById(commentCode);
    }
}
