package com.example.demo.controller;


import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequestDTO;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//    @PostMapping("/join")
//    public void join (@RequestParam String id,@RequestParam String pass,@RequestParam String name){
//
//        System.out.println("id:"+id );
//        System.out.println("pass:"+pass);
//        System.out.println("name:"+name);
//
//
//    }

    ////이런식도 있음
//@PostMapping("join{a}/{b}")
//        public void join(@PathVariable  String id, @PathVariable String pass){
//
//    System.out.println("id:"+id );
//    System.out.println("pass:"+pass);
//
//}

    // 3. Request body (json)
    @PostMapping("join")
    public User join(@RequestBody User user) {
        System.out.println("id : " + user.getId());
        System.out.println("pass : " + user.getPass());

        System.out.println("name : " + user.getName());
        return user;
    }


    @GetMapping("/v1/serch/member")
    public List<User> getUserAll() {
        System.out.println("일단 여기까진 옴");
        List<User> listUser = userService.getMemberALL();
        return listUser;

    }

    @GetMapping("/v1/serch/getUserByIdAndPass")
    public List<User> getUserByIdAndPass() {
        System.out.println("유저 조회까진");
        return userService.getUserByIdAndPass("id1", "1001");


    }


    @PostMapping("/v1/serch/addUser")
    public void createUser(@RequestBody UserRequestDTO userRequestDTO) {


        User user = new User(userRequestDTO);


        userService.addMember(user);


    }


    @DeleteMapping("/leave")
    public void leaveUser(@RequestParam String id) {

        userService.deleteById(id);


    }

//페이지 에서 값 단위로 뜯어옴
    //pagable
    @GetMapping("/user/page")
    public List<User> getUserWithPage(@RequestParam int page, @PageableDefault(size = 2) Pageable pageable ){
List<User> list= userService.getAll(pageable.withPage(page-1));

    return list;

    }


    @PutMapping("/update")
    public void updateUserById(@RequestBody UserRequestDTO userRequestDTO) {
        User user = null;
        user = new User(userRequestDTO);

        userService.updateById(user);
        


    }

}
