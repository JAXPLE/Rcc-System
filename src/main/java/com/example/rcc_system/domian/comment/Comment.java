package com.example.rcc_system.domian.comment;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "comment")
public class Comment {
    @Id
    private int commentCode;
    private int boardCode; //fk
    private String commentWriter; //fk
    private String commentContext;
    @JsonFormat(pattern="MM/DD")
    private Timestamp commentWriteDate;
    @JsonFormat(pattern="MM/DD")
    private Timestamp commentModifyDate;

    public Comment(CommentRequestDto commentRequestDto) {
        this.commentCode = commentRequestDto.getCommentCode();
        this.boardCode = commentRequestDto.getBoardCode();
        this.commentWriter = commentRequestDto.getCommentWriter();
        this.commentContext = commentRequestDto.getCommentContext();
        this.commentWriteDate = commentRequestDto.getCommentWriteDate();
        this.commentModifyDate = commentRequestDto.getCommentModifyDate();
    }

    public void setCommentContext(Comment comment) {
        this.commentContext = comment.getCommentContext();
    }
}
