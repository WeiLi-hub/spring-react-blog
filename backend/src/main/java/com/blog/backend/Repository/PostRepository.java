package com.blog.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.backend.Entity.Post;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
    
}
