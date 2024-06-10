package com.example.SWP391.controller;

import com.example.SWP391.payload.Request.VisitorSignUp;
import com.example.SWP391.payload.Request.VisitorSignUp;
import com.example.SWP391.service.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Operation(summary = "Đăng ký vistor để mua vé ", description = "API nàgiupsups đăng nhập.")
    @ApiResponse(responseCode = "201", description = "Người dùng được tạo thành công")
    @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    @PostMapping("/sign-up-visitor")
    public ResponseEntity<String> signUpVisitor(@RequestBody VisitorSignUp visitorSignUp) {
        try {
            visitorService.signUpVisitor(visitorSignUp);
            return ResponseEntity.ok("Visitor signed up successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
