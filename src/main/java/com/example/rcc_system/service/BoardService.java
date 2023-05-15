package com.example.rcc_system.service;

import com.example.rcc_system.domian.board.Board;
import com.example.rcc_system.domian.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository BOARD_REPOSITORY;

    public void addBoard(Board board) {
        BOARD_REPOSITORY.save(board);
    }

    public Board getBoardById(int boardCode){
        Board board = null;
        board = BOARD_REPOSITORY.findById(boardCode).orElseThrow(
                () -> new IllegalArgumentException("없는 보드")
        );
        return board;
    }

    @Transactional
    public void updateBoardContext(Board newBoard) {
//        System.out.println("boardCode : " + newBoard.getBoardCode());
        Board board = getBoardById(newBoard.getBoardCode());

        if (board != null)
            board.setBoardContext(newBoard);
    }

    @Transactional
    public void deleteBoard(int boardCode) {
        Board board = getBoardById(boardCode);

        if (board != null)
            BOARD_REPOSITORY.delete(board);
    }
}