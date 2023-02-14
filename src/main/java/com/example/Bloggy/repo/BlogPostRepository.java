package com.example.Bloggy.repo;

import com.example.Bloggy.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

}
