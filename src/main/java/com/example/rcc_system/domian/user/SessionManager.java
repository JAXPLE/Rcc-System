package com.example.rcc_system.domian.user;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SessionCookieName = "mySessionId";
    private Map<String, Client> sessionStore = new ConcurrentHashMap<>();

    public void createSession(Client client, HttpServletResponse response) {
        //세션 생성
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, client);

        //쿠키 생성 후 저장
        Cookie cookie = new Cookie(SessionCookieName, sessionId);
        response.addCookie(cookie);
    }

    public Client getSession(HttpServletRequest request) {
        Cookie cookie = findCookie(request, SessionCookieName);
        if (cookie == null) {
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    public void expire(HttpServletRequest request) {
        Cookie cookie = findCookie(request, SessionCookieName);
        if (cookie != null) {
            sessionStore.remove(cookie.getValue());
        }
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

}
