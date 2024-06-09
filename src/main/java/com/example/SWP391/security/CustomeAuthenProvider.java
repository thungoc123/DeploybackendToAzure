package com.example.SWP391.security;

import com.example.SWP391.entity.Account;
import com.example.SWP391.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class CustomeAuthenProvider implements AuthenticationProvider {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        Account account = accountRepository.findByEmail(email);
        if(account != null && account.getPassword().equals(password)){
         return authentication;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
//package com.example.SWP391.security;
//
//import com.example.SWP391.entity.Account;
//import com.example.SWP391.repository.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomeAuthenProvider implements AuthenticationProvider {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Sử dụng BCrypt để mã hóa mật khẩu
//    }
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = (String) authentication.getPrincipal();
//        String password = (String) authentication.getCredentials();
//
//        // Tìm tài khoản dựa trên email
//        Account account = accountRepository.findByEmail(email);
//        if (account == null) {
//            throw new BadCredentialsException("Invalid email or password");
//        }
//
//        // Kiểm tra mật khẩu đã mã hóa
//        if (!passwordEncoder.matches(password, account.getPassword())) {
//            throw new BadCredentialsException("Invalid email or password");
//        }
//
//        // Trả về một UsernamePasswordAuthenticationToken với quyền
//        return new UsernamePasswordAuthenticationToken(email, password,
//                Collections.singletonList(new SimpleGrantedAuthority("USER")));
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
