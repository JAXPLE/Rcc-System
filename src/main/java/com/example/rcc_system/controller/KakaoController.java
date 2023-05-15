package com.example.rcc_system.controller;


import com.example.rcc_system.domian.user.Client;
import com.example.rcc_system.domian.user.ClientRepository;
import com.example.rcc_system.kakao.KakaoRepository;
import com.example.rcc_system.service.KakaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/oauth")
public class KakaoController {

    private final KakaoService kakaoService;
    private final ClientRepository clientRepository;


    @GetMapping("/kakao")
    public String kakaoCallback(
            @RequestParam String code, HttpServletRequest request
    ) {
        String token = kakaoService.getKakaoAccessToken(code);
        String id = kakaoService.createKakaoUser(token);

        Client client = clientRepository.getClientBykakaoId(id);

        if (client == null) {
            return "redirect:/clientJoin?client_type=3&kakao_id=" + id;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("kakao", client.getKakaoId());
            session.setAttribute("log", client);

            return "redirect:/";
        }
    }

    @GetMapping("kakaoLogout")
    public String kakaoLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("log");
        session.removeAttribute("kakao");

        return "redirect:/";
    }

}
