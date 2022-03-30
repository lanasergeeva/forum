package ru.job4j.forum;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.services.PostServiceData;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class IndexTest {

    @MockBean
    protected PostServiceData service;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser
    public void whenTestHome() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/index.jsp"))
                .andExpect(model().attributeExists("posts", "user"));

    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("posts", "user"));
    }

    @Test
    @WithMockUser
    public void whenEdit() throws Exception {
        this.mockMvc.perform(get("/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("user"));
    }


    @Test
    @WithMockUser
    public void whenReply() throws Exception {
        this.mockMvc.perform(get("/reply?postId=22"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reply"))
                .andExpect(model().attributeExists("user", "reply"));
    }

    @Test
    @WithMockUser
    public void whenPost() throws Exception {
        this.mockMvc.perform(get("/post?postId=34"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser
    public void whenUpdatePost() throws Exception {
        Post post = Post.of("name", "description", new User());
        post.setId(34);
        when(service.findById(34)).thenReturn(post);
        this.mockMvc.perform(get("/update?postId=34"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("post", "user"))
                .andExpect(model().attribute("post",
                        hasProperty("id", is(34))))
                .andExpect(model().attribute("post",
                        hasProperty("name", is("name"))))
                .andExpect(model().attribute("post",
                        hasProperty("description", is("description"))));
    }

    @Test
    @WithMockUser
    public void whenPostWithoutParameterThen404() throws Exception {
        this.mockMvc.perform(get("/post?postId="))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser
    public void whenReplyWithoutParameterThen404() throws Exception {
        this.mockMvc.perform(get("/reply?postId="))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser
    public void whenDeleteWithoutParameterThen404() throws Exception {
        this.mockMvc.perform(get("/delete?acId="))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}