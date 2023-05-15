package com.example.demo.domain.board;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "board")



public class Board {


    @Id
    private int boardCode;

    @NonNull
    private String title;
    @NonNull
    private String writer;
    @NonNull
    private String contents;


public Board(BoardRequestDTO boardRequestDTO){

    this.boardCode=boardRequestDTO.getBoardCode();
    this.title=boardRequestDTO.getTitle();
    this.writer=boardRequestDTO.getWriter();
    this.contents=boardRequestDTO.getContents();



}
}
