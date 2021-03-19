package com.an.gradlespringboot.controller;



import com.an.gradlespringboot.entity.Result;
import com.an.gradlespringboot.entity.User;
import com.an.gradlespringboot.service.TokenService;
import com.an.gradlespringboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class JpaController {
    private static final Logger LOG = LoggerFactory.getLogger(JpaController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/user")
    public String userTest(){
        User user = userService.findByAge(19);
        LOG.info("user="+user.toString());
        return user.getName();
    }

    @GetMapping(path = "/login", params = "account")
    public Result login(String account){
        User user = userService.findByAccount(account);
        if (user != null){
            user.setPwd("");

            LOG.info("user="+user.toString());
            String token =  tokenService.createToken(user);
            LOG.info("token = " + token);
            user.setToken(token);
            boolean tokenVerify = tokenService.verifyToken(token);
            LOG.info("tokenVerify = " + tokenVerify);

            return Result.Success("登陆成功!",user);
        }
        return Result.Error("登陆失败!");
    }


    @RequestMapping(path = "/getUser/{userName}",method = RequestMethod.GET)
    public User getUser(@PathVariable String userName){
        User user = userService.findSql(userName);
        LOG.info("user="+user.toString());
        return user;
    }

    @GetMapping(path = "getUserByName",params = "userName",headers = "token")
    public Result getUserByName(String userName,@RequestHeader String token){
        User user = userService.findSql(userName);
        if (user != null){
            LOG.info("user="+user.toString());

            boolean tokenVerify = tokenService.verifyToken(token);
            LOG.info("tokenVerify = " + tokenVerify);
            if (tokenVerify){
                ArrayList<User> users = new ArrayList<>();
                users.add(user);
                return Result.Success("请求成功!",users);
            }else {
                return Result.Error("token验证失败!");
            }

        }
        return Result.Error("查找用户失败!");
    }
}
