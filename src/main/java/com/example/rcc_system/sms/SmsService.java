package com.example.rcc_system.sms;


import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class SmsService {

    public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
        String apiKey = "NCSJ6ZBDZIOFE5VI";
        String apiSecret = "3PFFRH6UXPHQARHQM391ICHUXVLJRCCO";
        Message coolsms = new Message(apiKey,apiSecret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", userPhoneNumber);    // 수신전화번호
        params.put("from", "01021105344");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "[개구리분리수거] 본인확인 인증번호는" + "["+randomNumber+"]" + "입니다."+"타인노출금지"); // 문자 내용 입력
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }
}
