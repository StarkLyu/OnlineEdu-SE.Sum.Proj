package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.PathMessage;
import com.se231.onlineedu.message.request.ReplyMessage;
import com.se231.onlineedu.model.Forum;
import com.se231.onlineedu.model.Reply;
import org.springframework.http.ResponseEntity;

import javax.xml.stream.events.EntityReference;

public interface ForumService {
    Forum updateForum(Forum forum);

    Forum getForum(String id);

    ResponseEntity<?> insertReply(String id, ReplyMessage replyMessage);

    Object getReplyOrForum(Forum forum, PathMessage pathMessage);
}
