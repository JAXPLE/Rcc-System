package com.example.rcc_system.domian.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

    @Query(nativeQuery = true, value = "SELECT * from comment where board_code = ?")
    public List<Comment> getBoardCodeComment(int boardCode);
}
