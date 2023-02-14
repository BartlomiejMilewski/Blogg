package com.example.Bloggy.controller;

import com.example.Bloggy.exceptions.ResourceNotFoundException;
import com.example.Bloggy.model.BlogPost;
import com.example.Bloggy.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/bloggy")

public class BlogPostController {
    @Autowired
    private BlogPostRepository blogPostRepository;


    public @ResponseBody Iterable<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }
    @PostMapping(path = "/posts")
    public BlogPost addPost(@RequestBody BlogPost blogPost){
        return blogPostRepository.save(blogPost);
    }

    @GetMapping(path="/posts/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Integer id){
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " does not exist"));
        return ResponseEntity.ok(blogPost);
    }
    @PutMapping(path="/posts/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Integer id, @RequestBody BlogPost blogPostDetails){
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " does not exist"));

        blogPost.setAuthor(blogPostDetails.getAuthor());
        blogPost.setTags(blogPostDetails.getTags());
        blogPost.setTitle(blogPostDetails.getTitle());
        blogPost.setContent(blogPostDetails.getContent());

        BlogPost updatedPost = blogPostRepository.save(blogPost);
        return ResponseEntity.ok(updatedPost);
    }
    @DeleteMapping(path ="/posts/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable Integer id){

        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " does not exist"));
        blogPostRepository.delete(blogPost);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Post with id "+ id + " deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
