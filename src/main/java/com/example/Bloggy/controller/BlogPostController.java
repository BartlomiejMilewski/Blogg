package com.example.Bloggy.controller;

import com.example.Bloggy.exceptions.ResourceNotFoundException;
import com.example.Bloggy.model.BlogPost;
import com.example.Bloggy.repo.BlogPostRepository;
import com.example.Bloggy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping(path = "/bloggy")

public class BlogPostController {
    @Autowired
    private PostService postService;

    public BlogPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts")
    public ResponseEntity<List<BlogPost>> getAllPosts() {
        List<BlogPost> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<BlogPost> addPost(@RequestBody BlogPost blogPost) {
        BlogPost newPost = postService.addPost(blogPost);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping(path="/posts/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Integer id){
        BlogPost blogPost = postService.findPostById(id);
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }
    @PutMapping(path="/posts/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Integer id, @RequestBody BlogPost blogPostDetails) {
        BlogPost blogPost = postService.findPostById(id);

        blogPost.setAuthor(blogPostDetails.getAuthor());
        blogPost.setTags(blogPostDetails.getTags());
        blogPost.setTitle(blogPostDetails.getTitle());
        blogPost.setContent(blogPostDetails.getContent());

        BlogPost updatedPost = postService.updatePost(blogPost);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }
    @DeleteMapping(path ="/posts/{id}")
    public ResponseEntity<BlogPost> deletePostById(@PathVariable Integer id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
