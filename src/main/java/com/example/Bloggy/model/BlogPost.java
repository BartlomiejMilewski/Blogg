package com.example.Bloggy.model;

import jakarta.persistence.*;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String author;
    private String tags;
    private String title;
    private String content;


    public BlogPost() {
    }

    public BlogPost(String author, String tags, String title, String content) {
        this.author = author;
        this.tags = tags;
        this.title = title;
        this.content = content;
    }

    public BlogPost(Integer id, String author, String tags, String title, String content) {
        this.id = id;
        this.author = author;
        this.tags = tags;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}