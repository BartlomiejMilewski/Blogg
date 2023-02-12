package com.example.Bloggy.controller;

import com.example.Bloggy.model.BlogPost;
import com.example.Bloggy.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/bloggy")

public class BlogPostController {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewPost(@RequestParam String author, @RequestParam String tags,
                                           @RequestParam String title, @RequestParam String content) {

        BlogPost post = new BlogPost();
        post.setAuthor(author);
        post.setTags(tags);
        post.setTitle(title);
        post.setContent(content);

        blogPostRepository.save(post);
        return "Saves";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    @GetMapping(path="/find/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable("id") Integer id){
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);
        if(blogPost.isPresent()) {
            return new ResponseEntity<>(blogPost.get(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deletePost(@RequestParam Integer id){
        blogPostRepository.deleteById(id);
        return "Post with id number" + id + "deleted";
    }

    @PutMapping(path="/update")
    public ResponseEntity<BlogPost> updatePost(@RequestBody BlogPost blogPost){
        BlogPost updatedPost = blogPostRepository.save(blogPost);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }


}
