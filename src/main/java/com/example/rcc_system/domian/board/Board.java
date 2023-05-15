package com.example.rcc_system.domian.board;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name="board")
public class Board {

    @Id
    private int boardCode; //pk
    private String clientCode; //fk
    private String boardWriter;
    private int boardType;
    private String boardTitle;
    private String boardContext;

    @CreatedDate
    private Timestamp boardGenerationDate;

    @LastModifiedBy
    private Timestamp boardModifyDate;

    public Board(BoardRequestDto boardRequestDto) {
        this.boardCode = boardRequestDto.getBoardCode();
        this.clientCode = boardRequestDto.getClientCode();
        this.boardWriter = boardRequestDto.getBoardWriter();
        this.boardType = boardRequestDto.getBoardType();
        this.boardTitle = boardRequestDto.getBoardTitle();
        this.boardContext = boardRequestDto.getBoardContext();
        this.boardGenerationDate = boardRequestDto.getBoardGenerationDate();
        this.boardModifyDate = boardRequestDto.getBoardModifyDate();
    }

    public void setBoardContext(Board board) {
        this.boardCode       = board.getBoardCode();
        this.boardTitle      = board.getBoardTitle();
        this.boardContext    = board.getBoardContext();
        this.boardModifyDate = board.getBoardModifyDate();
    }
}
