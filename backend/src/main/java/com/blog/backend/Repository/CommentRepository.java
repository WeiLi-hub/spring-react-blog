package com.blog.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.backend.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
