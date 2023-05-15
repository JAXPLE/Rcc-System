package com.example.demo.service;


import com.example.demo.domain.board.Board;
import com.example.demo.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

final BoardRepository boardRepository;




    public List<Board> getBoardALL() {


        return boardRepository.findAll();


    }




}
