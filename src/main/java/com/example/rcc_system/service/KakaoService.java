package com.example.rcc_system.service;

import com.example.rcc_system.domian.user.Client;
import com.example.rcc_system.domian.user.ClientRepository;
import com.example.rcc_system.kakao.Kakao;
import com.example.rcc_system.kakao.KakaoRepository;
import com.example.rcc_system.kakao.KakaoRequestDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoRepository kakaoRepository;
    private final ClientRepository clientRepository;


    public String getKakaoAccessToken(String code) {
        String accessToken = "";
//        String refreshToken = "";
        String reqUrl = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id=e8a5603f3be2edee2433cc080f00306c"); // TODO REST_API_KEY 입력
            stringBuilder.append("&redirect_uri=http://localhost:8080/oauth/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            stringBuilder.append("&code=" + code);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();

//            //200이상
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);


            //요청을 통해 얻은 json타입 response
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
//            System.out.println("response body : " + result);

            //gson 라이브러리에 포함된 클래스로 json 파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
//            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
//
//            System.out.println("refreshToken : " + refreshToken);
//            System.out.println("accessToken : " + accessToken);


            bufferedReader.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    public String createKakaoUser(String token) {

        String id = "";


        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token);


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String line = "";
            String result = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            JsonElement propertiesElement = element.getAsJsonObject().get("properties");

            id = element.getAsJsonObject().get("id").getAsString();
            String name = propertiesElement.getAsJsonObject().get("nickname").getAsString();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";
            if (hasEmail) {
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }


            boolean isRun = checkKakaoAccount(id);

            if (isRun) {
                KakaoRequestDto kakaoRequestDto = new KakaoRequestDto(id, name, email);
                createKakao(kakaoRequestDto);
            } else {
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void createKakao(KakaoRequestDto kakaoRequestDto) {
        Kakao kakao = new Kakao(kakaoRequestDto);
        kakaoRepository.save(kakao);
    }

    public boolean checkKakaoAccount(String kakaoId) {
        boolean isRun = false;

        Optional<Kakao> optional = kakaoRepository.getKakaoByid(kakaoId);

        if (!optional.isPresent()) {
            isRun = true;
        }
        return isRun;
    }



}