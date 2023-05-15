package com.example.demo.controller;

import com.example.demo.domain.board.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class BoardController {

private final BoardService boardService;



    @GetMapping("/v1/search/getBoardALL")
    public List<Board> getBoardAll(){
    List<Board> list =  boardService.getBoardALL();

        return list;
    }






}
