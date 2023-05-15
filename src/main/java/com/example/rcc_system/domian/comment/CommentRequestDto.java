package com.example.rcc_system.domian.comment;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private int commentCode; //pk
    private int boardCode; //fk
    private String commentWriter; //fk
    private String commentContext;
    private Timestamp commentWriteDate,CommentModifyDate;
}
