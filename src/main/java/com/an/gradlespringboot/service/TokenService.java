package com.an.gradlespringboot.service;

import com.an.gradlespringboot.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000;     // 7 days
    static final String TOKEN_SECRET = "P@ssw02d";     // JWT密码

    public String createToken(User user) {
        String token="";
        token= JWT.create()
                .withIssuer("auth0")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("username", user.getAccount())
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
        return token;
    }



    /**
     * 签名验证
     * @param token
     * @return
     */
    public boolean verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + jwt.getClaim("username").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }


}
