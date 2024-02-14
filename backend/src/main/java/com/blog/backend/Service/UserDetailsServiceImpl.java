package com.blog.backend.Service;

import org.springframework.stereotype.Service;

import com.blog.backend.Entity.BlogUser;
import com.blog.backend.Repository.BlogUserRepository;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final BlogUserRepository repository;

    public UserDetailsServiceImpl(BlogUserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        BlogUser blogUser = repository.findByUserName(username)
        .orElseThrow(() -> new UsernameNotFoundException("No blog user found with username: " + username));
        return this.mapToUser(blogUser);
    }

    private User mapToUser(BlogUser blogUser){
        return new User(
            blogUser.getUserName(), 
            blogUser.getPassword(), 
            getAuthorities("ROLE_" + blogUser.getAuthority()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user){
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
