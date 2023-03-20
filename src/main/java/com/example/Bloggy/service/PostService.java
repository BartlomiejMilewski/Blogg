package com.example.Bloggy.service;

import com.example.Bloggy.exceptions.ResourceNotFoundException;
import com.example.Bloggy.model.BlogPost;
import com.example.Bloggy.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class PostService {
    private final BlogPostRepository blogPostRepository;

    @Autowired
    public PostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }
    @ResponseBody
    public BlogPost addPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost updatePost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public BlogPost findPostById(Integer id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " does not exist"));
    }

    public void deletePost(Integer id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " does not exist"));
        blogPostRepository.deleteById(id);
    }


}
