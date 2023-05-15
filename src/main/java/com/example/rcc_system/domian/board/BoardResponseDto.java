package com.example.rcc_system.domian.board;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {

    private int size;
    private List<Board> list;

}
