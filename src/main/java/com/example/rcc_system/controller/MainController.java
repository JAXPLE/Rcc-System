package com.example.rcc_system.controller;

import com.example.rcc_system.domian.board.Board;
import com.example.rcc_system.domian.board.BoardRepository;
import com.example.rcc_system.domian.comment.Comment;
import com.example.rcc_system.domian.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/header")
    public String header(){
        return "header";
    }
    @GetMapping("/footer")
    public String footer(){
        return "footer";
    }
    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/clientJoin")
    public String join(){
        return "/client/clientJoin";
    }
    @GetMapping("/clientJoinAgree")
    public String joinAgree(){
        return "/client/clientJoinAgree";
    }
    @GetMapping("/clientLogin")
    public String clientlogin(){
        return "/client/clientLogin";
    }

    @GetMapping("/clientLeave")
    public String clientLeave(){
        return "/client/clientLeave";
    }
    @GetMapping("/clientUpdate")
    public String clientUpadate(){
        return "/client/clientUpadate";
    }
    @GetMapping("/clientMypage")
    public String clientMypage(){
        return "/client/clientMypage";
    }

    @GetMapping("/boardList")
    public String board(){
        return "/board/boardList";
    }

    @GetMapping("/boardWrite")
    public String boardWrite(){
        return "/board/boardWrite";
    }

    @GetMapping("/boardUpdate")
    public String boardUpdate(){
        return "/board/boardUpdate";
    }

    @GetMapping("/commentList")
    public String commentList(){return "comment/commentList";}

    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam int boardCode , Model model){
        Board board = boardRepository.findById(boardCode).orElseThrow(
                () -> new IllegalArgumentException("없는보드")
        );
        model.addAttribute("board",board);
        model.addAttribute("commentList",commentRepository.getBoardCodeComment(boardCode));
        return "board/boardDetail";
    }

    @GetMapping("/orderForm")
    public String orderForm(){
        return "order/orderForm";
    }

    @GetMapping("/orderFormPopup")
    public String orderFormPopup(){
        return "order/orderFormPopup";
    }
    @PostMapping("/orderFormPayment")
    public String orderFormPayment(){
        return "order/orderFormPayment";
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("log");
        session.removeAttribute("name");
        return "redirect:/";
    }
    @GetMapping("adminPage")
    public String adminPage(){
        return "client/adminPage";
    }


}