package com.example.Bloggy.controller;

import com.example.Bloggy.model.BlogPost;
import com.example.Bloggy.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/bloggy")

public class BlogPostController {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewPost (@RequestParam String author, @RequestParam String tags,
                                            @RequestParam String title, @RequestParam String content){

        BlogPost post = new BlogPost();
        post.setAuthor(author);
        post.setTags(tags);
        post.setTitle(title);
        post.setContent(content);

        blogPostRepository.save(post);
        return "Saves";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<BlogPost> getAllPosts() {
        // This returns a JSON or XML with the users
        return blogPostRepository.findAll();
    }


}
