package com.example.rcc_system.domian.user;

import com.example.rcc_system.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class ClientRequestDto {


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


}