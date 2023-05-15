package com.example.rcc_system.domian.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;



@NoArgsConstructor(force = true)
@AllArgsConstructor

@Getter
@Table(name="client")
@Entity
public class Client {


    @Id//String id 맴버가 테이블 안에서 PK 임을 알려줌
    private int clientCode;

    private String clientName;
    private String clientPhone;
    private String clientPassword;
    private String clientAddress;
    private int clientType;
    private LocalDateTime clientRegistDate;
    private LocalDateTime clientModifyDate;
    private boolean clientIsused;
    private String kakaoId;

    public Client(ClientRequestDto clientRequestDto){


        this.clientName = clientRequestDto.getClientName();
        this.clientPhone = clientRequestDto.getClientPhone();
        this.clientPassword = clientRequestDto.getClientPassword();
        this.clientAddress = clientRequestDto.getClientAddress();
        this.clientType = clientRequestDto.getClientType();
        this.clientIsused = clientRequestDto.isClientIsused();
        this.clientRegistDate = LocalDateTime.now();
        this.kakaoId = clientRequestDto.getKakaoId();

    }
    public void setClient(Client client){

        this.clientPassword = client.clientPassword;
        this.clientAddress = client.clientAddress;
        this.clientModifyDate = LocalDateTime.now();

    }
    public void deleteClient(){
        this.clientPhone = null;
        this.clientName = null;
        this.clientPassword = null;
        this.clientAddress = null;
        this.clientType = 0;
        this.clientModifyDate = LocalDateTime.now();
        this.clientIsused = false;
        this.kakaoId = null;
    }


}