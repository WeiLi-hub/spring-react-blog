package com.blog.backend.Repository;

import com.blog.backend.Entity.BlogUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Long>{
    Optional<BlogUser> findByUserName(String username);
}
