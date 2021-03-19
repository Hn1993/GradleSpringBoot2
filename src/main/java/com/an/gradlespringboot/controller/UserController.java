package com.an.gradlespringboot.controller;


import com.an.gradlespringboot.entity.Result;
import com.an.gradlespringboot.entity.User;
import com.an.gradlespringboot.service.TokenService;
import com.an.gradlespringboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * /user 需要携带token
 */
@RestController()
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping(path = "/getUser",params = "account")
    public Result getUser(String account){
        User user = userService.findByAccount(account);
        if (user != null){
            user.setPwd("");
            return Result.Success("请求成功!",user);
        }
        return Result.Error("查找用户失败!");
    }

    /**
     * 分页请求
     * @param age
     * @param page
     * @param size
     * @return
     */
    @GetMapping(path = "/getUserPage")
    public Result getUserPage(@RequestParam int age,@RequestParam int page,@RequestParam int size){
        Page<User> userPage = userService.findUsersByAge(age,page,size);
        if (userPage != null){
            return Result.Success("请求成功!",userPage.getContent());
        }
        return Result.Error("查找用户失败!");
    }

}
