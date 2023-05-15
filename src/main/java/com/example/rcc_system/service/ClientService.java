package com.example.rcc_system.service;

import com.example.rcc_system.domian.user.Client;
import com.example.rcc_system.domian.user.ClientRepository;
import com.example.rcc_system.kakao.KakaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor

public class ClientService {

    private final ClientRepository clientRepository;
    private final KakaoRepository kakaoRepository;


    //회원가입
    public Client addClient(Client client) {
        Client result = clientRepository.save(client);


        return result;
    }


    //로그인
    public Client login(String clientPhone, String clientPassword) {

        Client client = clientRepository.login(clientPhone, clientPassword);


        return client;
    }


    //중복확인
    public Client phoneDuplication(String clientPhone) {
        Client client = clientRepository.getClientByPhone(clientPhone);

        return client;
    }

    //회원수정
    @Transactional
    public void updateClient(Client client) {
        Client member = clientRepository.getClientByPhone(client.getClientPhone());

        //수정
        if (client != null) {
            member.setClient(client);
        }

    }

    //회원삭제
    @Transactional
    public void deleteClient(Client client) {
        Client cl = clientRepository.getById(client.getClientCode());

        if (cl.getClientType() == 3) {
            kakaoRepository.deleteById(cl.getKakaoId());
        }

        cl.deleteClient();

    }


}