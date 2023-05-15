package com.example.rcc_system.domian.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private int boardCode; //pk
    private String clientCode; //fk
    private String boardWriter;
    private int boardType;
    private String boardTitle,boardContext;
    private Timestamp BoardGenerationDate,boardModifyDate;
}
