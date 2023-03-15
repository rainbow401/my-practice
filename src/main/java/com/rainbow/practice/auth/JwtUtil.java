package com.rainbow.practice.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rainbow.practice.model.User;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: YanZhiHao
 * @Date: 2023/3/15
 * @Description: jwt token 工具
 */
public class JwtUtil {

    private final AuthProperties authProperties = new AuthProperties();

    public Boolean checkToken(@RequestHeader("Authorization") String token) {
        //use more secure key
        Algorithm algorithm = Algorithm.HMAC256(authProperties.getSecret());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(authProperties.getIssuer())
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token.replace("Bearer ", ""));
        return true;
    }

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(authProperties.getSecret());
            Date date = new Date(System.currentTimeMillis() + authProperties.getExp());
            return JWT.create()
                    .withIssuer(authProperties.getIssuer())
                    .withClaim("user", "nihao")
                    .withExpiresAt(date)
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
            return "生成token失败";
        }
    }
}
