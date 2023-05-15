package com.example.rcc_system.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="kakao")
public class Kakao {
    @Id
    private String kakaoId;
    private String kakaoName;
    private String kakaoEmail;


    public Kakao(KakaoRequestDto kakaoRequestDto){
        this.kakaoId = kakaoRequestDto.getKakaoId();
        this.kakaoName = kakaoRequestDto.getKakaoName();
        this.kakaoEmail = kakaoRequestDto.getKakaoEmail();

    }



}