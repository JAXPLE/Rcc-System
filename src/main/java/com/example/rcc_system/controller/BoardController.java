package com.example.rcc_system.controller;

import com.example.rcc_system.domian.board.Board;
import com.example.rcc_system.domian.board.BoardRepository;
import com.example.rcc_system.domian.board.BoardRequestDto;
import com.example.rcc_system.domian.board.BoardResponseDto;
import com.example.rcc_system.domian.search.SearchRequestDto;
import com.example.rcc_system.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository BOARD_REPOSITORY;
    private final BoardService BOARD_SERVICE;
    private Page<Board> pageResult;
    private BoardResponseDto result;

    @PostMapping("addBoard")
    public void boardAdd(@RequestBody BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
//        System.out.println(board);
        BOARD_SERVICE.addBoard(board);
    }
    @GetMapping("getBoard")
    public Board boardDetail(@RequestParam int boardCode){
        return BOARD_SERVICE.getBoardById(boardCode);
    }

    @PostMapping("searchGetBoard")
    public BoardResponseDto searchBoard(
            @RequestBody SearchRequestDto searchRequestDto,
            @PageableDefault(size = 10) Pageable pageable) {

        result = new BoardResponseDto();
        System.out.println(searchRequestDto);

        String searchType = searchRequestDto.getSearchType();
        String searchText = searchRequestDto.getSearchText();
        int page = searchRequestDto.getPage();

        Sort sort = Sort.by(Sort.Direction.DESC, "boardGenerationDate");
        Pageable pageableWithSort = PageRequest.of(page, pageable.getPageSize(), sort);


        switch (searchType) {
            case "ALL":
                System.out.println("ALL");
                pageResult = BOARD_REPOSITORY.findAll(pageableWithSort);
                break;
            case "CODE":
                System.out.println("CODE");
                pageResult = BOARD_REPOSITORY.findBoardByBoardCode(
                        pageableWithSort, Integer.parseInt(searchRequestDto.getSearchText())
                );
                break;
            case "TITLE":
                System.out.println("TITLE");
                pageResult = BOARD_REPOSITORY.findByBoardTitleContaining(
                        pageableWithSort, searchText
                );
                break;
            case "CONTEXT":
                System.out.println("CONTEXT");
                pageResult = BOARD_REPOSITORY.findByBoardContextContaining(
                        pageableWithSort, searchText
                );
                break;
            case "TITLE_AND_CONTEXT":
                System.out.println("TITLE_AND_CONTEXT");
                pageResult = BOARD_REPOSITORY.findByBoardTitleContainingOrBoardContextContaining(
                        pageableWithSort, searchText, searchText
                );
                break;
            case "WRITER":
                System.out.println("WRITER");
                pageResult = BOARD_REPOSITORY.findByBoardWriterContaining(
                        pageableWithSort, searchText
                );
                break;
            default: break;
        }


        result.setList(pageResult.getContent());
        result.setSize(pageResult.getTotalPages());

        return result;
    }

    @PutMapping("updateBoard")
    public void updateBoard(@RequestBody BoardRequestDto boardRequestDto) {
//        System.out.println("boardRequestDto.getBoardCode() : " + boardRequestDto.getBoardCode());
        Board board = new Board(boardRequestDto);
        BOARD_SERVICE.updateBoardContext(board);
    }
    @DeleteMapping ("deleteBoard")
    public String deleteBoard(@RequestParam String boardCode) {
        BOARD_SERVICE.deleteBoard(Integer.parseInt(boardCode));
        return "boardList";
    }

}