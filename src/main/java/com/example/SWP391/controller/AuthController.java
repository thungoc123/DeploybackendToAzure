package com.example.SWP391.controller;

import com.example.SWP391.Payload.Response.BaseResponse;
import com.example.SWP391.utils.JwtHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.io.Encoders;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/api-auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @PostMapping("/login")
    public ResponseEntity<?> sinIn(@RequestParam String email , @RequestParam String password) {

//        SecretKey key = Jwts.SIG.HS256.key().build(); //or HS384.key() or HS512.key()
//        String keys = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println("kiem tra" + keys);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email , password);
        authenticationManager.authenticate(token);
        String jwtToken =  jwtHelper.genertateToken("hello jwt" +email + "" + password);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setData(jwtToken);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
