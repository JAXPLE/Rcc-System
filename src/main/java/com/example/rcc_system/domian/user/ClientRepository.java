package com.example.rcc_system.domian.user;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM client WHERE client_phone=? AND client_password=?")
    public Client login(String Phone, String password);


    @Query(nativeQuery = true, value="SELECT * FROM client WHERE client_phone=?")
    public Client getClientByPhone(String Phone);

    @Query(nativeQuery = true, value="SELECT * FROM client WHERE kakao_id=?")
    public Client getClientBykakaoId(String id);



    @Query(nativeQuery = true, value="SELECT client_code FROM client WHERE client_type = 2 ORDER BY RAND() LIMIT 1;")
    public int getClientCodeBytype();



}
