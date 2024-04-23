package com.blog.backend.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.backend.Entity.BlogUser;
import com.blog.backend.Repository.BlogUserRepository;
import com.blog.backend.Security.JwtProvider;
import com.blog.backend.dto.AuthRequest;

@Service
public class AuthService {
    private final BlogUserRepository blogUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthService(
        BlogUserRepository blogUserRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        JwtProvider jwtProvider
    ){
        this.blogUserRepository = blogUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }
    
    private BlogUser mapToBlogUser(AuthRequest authRequest){
        return new BlogUser(
            authRequest.getUserName(),
            "USER",
            passwordEncoder.encode(authRequest.getPassword()),
            authRequest.getEmail()
        );
    }

    public boolean signup(AuthRequest authRequest){
        BlogUser blogUser = this.mapToBlogUser(authRequest);
        return blogUserRepository.findByUserName(blogUser.getUserName())
                .map(user -> false)
                .orElseGet(() ->{
                    blogUserRepository.save(blogUser);
                    return true;
                });
    }

    
}
