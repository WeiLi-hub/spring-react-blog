package com.blog.backend.Entity;

import jakarta.persistence.*;
// import org.hibernate.annotations.Comments;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "blog_user")
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "authority")
    private String authority;

    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "blogUser", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "blogUser", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY )
    private List<Comment> comments;

    public BlogUser() {
    }

    public BlogUser(String userName, String authority, String password, String email) {
        this.userName = userName;
        this.authority = authority;
        this.password = password;
        this.email = email;
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post){
        this.posts.add(post);
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}