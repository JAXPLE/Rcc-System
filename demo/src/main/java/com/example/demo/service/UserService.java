package com.example.demo.service;


import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service//서비스는 데이터 흐름의 순서를 보장한다고 함
@RequiredArgsConstructor //이거 다시 공부

public class UserService {

    @Autowired
    private final UserRepository userRepository;


    //CRUD
    public List<User> getMemberALL() {


        return userRepository.findAll();


    }


    public void addMember(User user){

        userRepository.save(user);

    }


    public User getMemberById(String id) {

        User user = userRepository.findById(id).orElse(null);

        return user;
    }

    public List<User>  getUserByIdAndPass(String id, String pass){

        return userRepository.getUserByIdAndPass(id, pass);

    }


public List<User> getAll(Pageable pageable){


        return userRepository.findAll(pageable).getContent();

}




    @Transactional
    public void updateById(User member){
        User user = getMemberById(member.getId());

        if(user !=null){

            user.setUser(member);

        }


    }



    @Transactional
public void deleteById(String id){
        System.out.println("삭제 시작");
        User user = getMemberById(id);
        
        if(user!=null){


            userRepository.deleteById(id);
            System.out.println("유저삭제완료");

        }else{
            System.out.println("유저가 없음");
            
        }
        
}


}
