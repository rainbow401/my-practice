package com.practice.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.practice.auth.service.UserService;
import com.practice.auth.entity.dto.LoginDTO;
import com.practice.auth.service.TokenStoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AuthorizationController {

    private final AuthProperties authProperties;

    private final TokenStoreService tokenStoreService;

    private final UserService userService;

    @PostMapping("/login")
    public Boolean login(LoginDTO dto) {
        return userService.login(dto);
    }

    @GetMapping("/check")
    public Boolean checkToken(@RequestHeader("Authorization") String token) {
        Algorithm algorithm = Algorithm.HMAC256(authProperties.getSecret()); //use more secure key
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(authProperties.getIssuer())
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token.replace("Bearer ", ""));
        return true;
    }
}
