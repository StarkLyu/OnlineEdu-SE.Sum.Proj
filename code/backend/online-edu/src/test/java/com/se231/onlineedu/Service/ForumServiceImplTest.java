package com.se231.onlineedu.Service;

import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.message.request.ReplyMessage;
import com.se231.onlineedu.model.Forum;
import com.se231.onlineedu.model.Reply;
import com.se231.onlineedu.repository.ForumRepository;
import com.se231.onlineedu.service.ForumService;
import com.se231.onlineedu.serviceimpl.ForumServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
public class ForumServiceImplTest {
    @TestConfiguration
    static class ForumServiceImplTestConfig{
        @Bean
        public ForumService forumService(){
            return new ForumServiceImpl();
        }
    }

    @Autowired
    private ForumService forumService;

    @MockBean
    private ForumRepository forumRepository;

    @Test
    public void getForumsBySec(){
        Forum forum = new Forum();
        forum.setUserId(1L);
        forum.setSecNo(1);
        forum.setUserId(1L);
        forum.setId("uftf");

        List<Forum> forums = new ArrayList<>();
        forums.add(forum);

        Mockito.when(forumRepository.findByCourseIdAndSecNo(1L,1)).thenReturn(forums);
        List<Forum> founds = forumService.getForumsBySection(1L,1);
        assertThat(founds.size()).isEqualTo(1);
        assertThat(founds.get(0).getId()).isEqualTo("uftf");
    }

    @Test
    public void getForumByCourse(){
        Forum forum = new Forum();
        forum.setUserId(1L);
        forum.setSecNo(1);
        forum.setUserId(1L);
        forum.setId("uftf");

        List<Forum> forums = new ArrayList<>();
        forums.add(forum);

        Mockito.when(forumRepository.findByCourseId(1L)).thenReturn(forums);
        List<Forum> founds = forumService.getForumsByCourse(1L);
        assertThat(founds.size()).isEqualTo(1);
        assertThat(founds.get(0).getId()).isEqualTo("uftf");
    }

    @Test(expected = NotFoundException.class)
    public void getEmptyForum(){
        Mockito.when(forumRepository.findById(anyString())).thenReturn(Optional.empty());
        forumService.getForum("adgv");
    }

    @Test
    public void getForumById(){
        Forum forum = new Forum();
        forum.setUserId(1L);
        forum.setSecNo(1);
        forum.setUserId(1L);
        forum.setId("uftf");

        Optional<Forum> forumOptional = Optional.of(forum);
        Mockito.when(forumRepository.findById("uftf")).thenReturn(forumOptional);
        Forum found = forumService.getForum("uftf");
        assertThat(found.getUserId()).isEqualTo(1L);
        assertThat(found.getSecNo()).isEqualTo(1);
    }

//    @Test
//    public void insertReply(){
//        Forum forum = new Forum();
//        forum.setUserId(1L);
//        forum.setSecNo(1);
//        forum.setUserId(1L);
//        forum.setId("uftf");
//
//        Optional<Forum> forumOptional = Optional.of(forum);
//        Mockito.when(forumRepository.findById("uftf")).thenReturn(forumOptional);
//
//
//        Reply reply = new Reply();
//        reply.setContent("content");
//        reply.setLikes(1234);
//        reply.setUserId(1L);
//        ReplyMessage replyMessage = new ReplyMessage(List.of(), reply);
//        Forum found = forumService.insertReply("uftf", replyMessage);
//        assertThat(found.getReplies().size()).isEqualTo(1);
//        assertThat(found.getReplies().get(0).getContent()).isEqualTo("content");
//
//
//    }

}
