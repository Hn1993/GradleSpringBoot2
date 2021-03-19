package com.an.gradlespringboot.controller;


import com.an.gradlespringboot.entity.Result;
import com.an.gradlespringboot.entity.User;
import com.an.gradlespringboot.service.TokenService;
import com.an.gradlespringboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * /public 不需要携带token
 */
@RestController()
@RequestMapping("/public")
public class PublicController {
    private static final Logger LOG = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping(path = "/test")
    public Result publicTest(){
        return Result.Success("publicTest","ojbk");
    }


    @PostMapping(path = "/testException")
    public Result publicTestException(){
        int i = 1;
//        i = i / 0;
        return Result.Success("publicTest","ojbk");
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
}
