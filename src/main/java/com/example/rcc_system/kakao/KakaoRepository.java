package com.example.rcc_system.kakao;

import com.example.rcc_system.domian.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KakaoRepository extends JpaRepository<Kakao, String> {

    @Query(nativeQuery = true, value="SELECT * FROM kakao WHERE kakao_id=?")
    public Optional<Kakao> getKakaoByid(String id);





}
