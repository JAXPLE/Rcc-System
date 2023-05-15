package com.example.rcc_system.domian.board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface BoardRepository extends JpaRepository<Board, Integer> {

    Page<Board> findBoardByBoardCode(Pageable pageable, int boardCode);
    Page<Board> findByBoardTitleContaining(Pageable pageable,String search);
    Page<Board> findByBoardContextContaining(Pageable pageable,String search);
    Page<Board> findByBoardTitleContainingOrBoardContextContaining(Pageable pageable,String searchTitle, String searchContext);
    Page<Board> findByBoardWriterContaining(Pageable pageable,String writer);
}