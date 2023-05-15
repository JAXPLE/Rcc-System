package com.example.demo.domain.user;

import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface UserRepository extends JpaRepository<User, String> {



    @Query(nativeQuery = true, value = "SELECT * FROM member")
    public List<User> func();


@Query(nativeQuery = true, value = "SELECT * member WHERE id=?1")
    public User func2(String id);


//SELECT * FROM membet WHERE id=?
public List<User> getAllById(String id);



public List<User> getUserByIdAndPass(String id, String pass);



}
