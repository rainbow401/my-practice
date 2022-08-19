package com.practice.domain.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.practice.entity.User;
import lombok.AllArgsConstructor;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor(onConstructor_ = {@Resource})
public class TokenStore {
    private final AuthProperties authProperties;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(authProperties.getSecret());
            Date date = new Date(new Date().getTime() + authProperties.getExp());
            return JWT.create()
                    .withIssuer(authProperties.getIssuer())
                    .withClaim("user", "nihao")
                    .withExpiresAt(date)
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            e.printStackTrace();
            return "生成token失败";
        }
    }
}
