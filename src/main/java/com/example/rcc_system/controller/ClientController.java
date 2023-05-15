package com.example.rcc_system.controller;


import com.example.rcc_system.domian.user.Client;
import com.example.rcc_system.domian.user.ClientRepository;
import com.example.rcc_system.domian.user.ClientRequestDto;
import com.example.rcc_system.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;
    //회원가입
    @PostMapping("join")
    public Client createClient(@RequestBody ClientRequestDto clientRequestDto){

        Client client = new Client(clientRequestDto);
        Client result = clientService.addClient(client);

        return result;
    }
    //로그인
    @PostMapping("login")
    public Boolean loginClient(@RequestParam("clientPhone") String clientPhone, @RequestParam("clientPassword") String clientPassword, HttpServletRequest request, HttpServletResponse response) {
        Client client = clientService.login(clientPhone, clientPassword);

        boolean isRun = false ;

        if (client != null) {
            // 세션에 사용자 정보 설정
            HttpSession session = request.getSession();
            session.setAttribute("log",client);
            isRun = true;
        }

        return isRun;
    }

    //

    //핸드폰번호 중복
    @PostMapping("duplication")
    public boolean duplication(@RequestParam("clientPhone") String clientPhone){
        Client client = clientService.phoneDuplication(clientPhone);

        boolean isRun = false;

        if(client != null){
            isRun = true;
        }
        return isRun;

    }
    //회원 업데이트
    @PostMapping("update")
    public void Update(@RequestBody ClientRequestDto clientRequestDto, HttpServletRequest request){
        Client client = new Client(clientRequestDto);
        HttpSession session = request.getSession();

        clientService.updateClient(client);
        session.removeAttribute("log");

    }

    //회원 정보 불러오기
    @GetMapping("getClient")
    public Client getClient(HttpServletRequest request){
        HttpSession session = request.getSession();
        Client client =(Client) session.getAttribute("log");

        return client;
    }
    // 회원삭제
    @PostMapping("delete")
    public boolean deleteClient(@RequestParam("clientPassword") String clientPassword,  HttpServletRequest request){
        HttpSession session = request.getSession();
        Client client =(Client) session.getAttribute("log");

        boolean isRun = true;

        if(clientPassword.equals(client.getClientPassword())){
            clientService.deleteClient(client);
            session.removeAttribute("log");
        }
        else{
            isRun = false;
        }
        return isRun;
    }








}