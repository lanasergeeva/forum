package ru.job4j.forum.control;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.services.PostServiceData;
import ru.job4j.forum.services.ReplyServices;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class IndexControlTest {

    @MockBean
    protected PostServiceData posts;

    @MockBean
    private ReplyServices replies;

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
        when(posts.findById(34)).thenReturn(post);
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

    @Test
    @WithMockUser
    public void whenSavePost() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name", "Куплю ладу-грант. Дорого.")
                        .param("description", "Год выпуска от 2020"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        MatcherAssert.assertThat(argument.getValue().getDescription(), is("Год выпуска от 2020"));
    }

    @Test
    @WithMockUser
    public void whenDeletePost() throws Exception {
        this.mockMvc.perform(get("/delete")
                        .param("acId", "34"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(posts).delete(argument.capture());
        MatcherAssert.assertThat(argument.getValue(), is(34));
    }

    @Test
    @WithMockUser
    public void whenDeleteReply() throws Exception {
        this.mockMvc.perform(get("/repdelete")
                        .param("repId", "29")
                        .param("postId", "34"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(replies).deleteReply(argument.capture());
        MatcherAssert.assertThat(argument.getValue(), is(29));
    }
}