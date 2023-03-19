package com.example.Bloggy;

import com.example.Bloggy.model.BlogPost;
import com.example.Bloggy.service.PostService;

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
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        // Setup mocked service
        BlogPost mockPosts = new BlogPost(1, "Test Author", "Test tags", "Test title", "Test content");
        doReturn(mockPosts).when(service).findPostById(1);

        mockMvc.perform(get("/bloggy/posts/{id}", 1))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author", is("Test Author")))
                .andExpect(jsonPath("$.tags", is("Test tags")))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

}
