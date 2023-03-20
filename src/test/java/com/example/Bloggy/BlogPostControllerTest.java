package com.example.Bloggy;

import com.example.Bloggy.model.BlogPost;
import com.example.Bloggy.service.PostService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BlogPostControllerTest {

    @MockBean
    private PostService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /posts/1 - Found")
    void testGetBlogPostById() throws Exception {
        // Setup mocked product
        BlogPost mockPosts = new BlogPost(1, "Test Author", "Test tags", "Test title", "Test content");
        // Setup mocked service
        doReturn(mockPosts).when(service).findPostById(1);
        //Execute get request
        mockMvc.perform(get("/bloggy/posts/{id}", 1))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author", is("Test Author")))
                .andExpect(jsonPath("$.tags", is("Test tags")))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }


    @Test
    @DisplayName("POST /posts - Created")
    void testPostBlogPost() throws Exception {
        // Setup mocked product
        BlogPost mockPosts = new BlogPost(1, "Test Author", "Test tags", "Test title", "Test content");
        // Setup mocked service
        doReturn(mockPosts).when(service).addPost(any(BlogPost.class));
        //Execute post request
        mockMvc.perform(post("/bloggy/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Test Author\",\"tags\":\"Test tags\",\"title\":\"Test title\",\"content\":\"Test content\"}")
                )

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author", is("Test Author")))
                .andExpect(jsonPath("$.tags", is("Test tags")))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }


    @Test
    @DisplayName("PUT /posts/{id} - Updated")
    void testUpdateBlogPost() throws Exception {
        // Setup mocked product
        BlogPost mockPosts = new BlogPost(1, "Test Author", "Test tags", "Test title", "Test content");
        BlogPost updatedPost = new BlogPost("Test Author", "Test tags", "Test title", "Test content");
        // Setup mocked service
        doReturn(mockPosts).when(service).findPostById(1);
        doReturn(mockPosts).when(service).updatePost(any(BlogPost.class));
        //Execute update request
        mockMvc.perform(put("/bloggy/posts/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Test Author\",\"tags\":\"Test tags\",\"title\":\"Test title\",\"content\":\"Test content\"}")

        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author", is("Test Author")))
                .andExpect(jsonPath("$.tags", is("Test tags")))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content")));


    }



    @Test
    @DisplayName("PUT /posts/{id} - Updated")
    void testDeleteBlogPost() throws Exception {
        // Setup mocked product
        BlogPost mockPosts = new BlogPost(1, "Test Author", "Test tags", "Test title", "Test content");
        // Setup mocked service
        doReturn(mockPosts).when(service).findPostById(1);
        doNothing().when(service).deletePost(1);
        //Execute delete request
        mockMvc.perform(delete("/bloggy/posts/{id}", 1))
                .andExpect(status().isOk());

    }


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
