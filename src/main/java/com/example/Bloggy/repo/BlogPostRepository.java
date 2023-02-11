package com.example.Bloggy.repo;

import com.example.Bloggy.model.BlogPost;
import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {

}
