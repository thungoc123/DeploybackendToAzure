package com.example.SWP391.controller;

import com.example.SWP391.Payload.Request.SponsorSignUp;
import com.example.SWP391.model.dto.SponsorDto;
import com.example.SWP391.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-sponsor")
public class SponsorController {
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/sign-up-sponsor")
    public ResponseEntity<String> signUpVisitor(@RequestBody SponsorSignUp sponsorSignUp) {
        try {
            sponsorService.signUpSponsor(sponsorSignUp);
            return ResponseEntity.ok("Sponsor signed up successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<SponsorDto>> getAllSponsors() {
        List<SponsorDto> sponsors = sponsorService.getAllSponsors();
        return ResponseEntity.ok(sponsors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorDto> getSponsorById(@PathVariable Long id) {
        Optional<SponsorDto> sponsorOptional = sponsorService.getSponsorById(id);
        return sponsorOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
