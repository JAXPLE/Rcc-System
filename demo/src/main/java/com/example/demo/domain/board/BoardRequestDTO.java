package com.example.demo.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class BoardRequestDTO {


    private int boardCode;

    private String title;

    private String writer;

    private String contents;



}
