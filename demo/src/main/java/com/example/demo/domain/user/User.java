package com.example.demo.domain.user;


import com.example.demo.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(force = true)
@Getter
@AllArgsConstructor
@Entity
@Table(name = "member")

public class User extends Timestamp {




    @Id
    private String id;

    @NonNull
    private String pass;

    @NonNull
    private String name;

    public User(UserRequestDTO userRequestDTO) {

        this.id = userRequestDTO.getId();
        this.pass = userRequestDTO.getPass();
        this.name = userRequestDTO.getName();


    }
public void setUser( User user){

        this.pass=user.getPass();




}




}

