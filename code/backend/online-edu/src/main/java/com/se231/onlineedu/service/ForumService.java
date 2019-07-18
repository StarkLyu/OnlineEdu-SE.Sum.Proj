package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.PathMessage;
import com.se231.onlineedu.message.request.ReplyMessage;
import com.se231.onlineedu.model.Forum;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ForumService {
    Forum updateForum(Forum forum);

    Forum getForum(String id);

    Forum insertReply(String id, ReplyMessage replyMessage);

    Object getReplyOrForum(Forum forum, PathMessage pathMessage);

    List<Forum> getForumsByCourse(Long courseId);

    List<Forum> getForumsBySection(Long courseId, int secNo);
}
