package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.message.request.PathMessage;
import com.se231.onlineedu.message.request.ReplyMessage;
import com.se231.onlineedu.model.Forum;
import com.se231.onlineedu.model.Reply;
import com.se231.onlineedu.repository.ForumRepository;
import com.se231.onlineedu.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumRepository forumRepository;

    @Override
    public  Forum updateForum(Forum forum){
        return forumRepository.save(forum);
    }

    @Override
    public Forum getForum(String id){
        return forumRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public ResponseEntity<?> insertReply(String id, ReplyMessage replyMessage){
        List<Integer> path = replyMessage.getPath();
        Reply reply = replyMessage.getReply();
        Forum forum = getForum(id);
        List<Reply> replies = forum.getReplies();
        for (int index : path) {
            if(index >= replies.size()){
                return ResponseEntity.badRequest().body("out of index");
            }
            replies = replies.get(index).getReplies();
        }
        replies.add(reply);
        return ResponseEntity.ok(updateForum(forum));
    }

    @Override
    public Object getReplyOrForum(Forum forum, PathMessage pathMessage){
        Reply reply = new Reply();
        List<Integer> path = pathMessage.getPath();
        if (path.isEmpty()) {
            return forum;
        } else {
            for (int index = 0; index < path.size(); index++) {
                if (index == 0) {
                    reply = forum.getReplies().get(path.get(0));
                } else {
                    reply = reply.getReplies().get(path.get(index));
                }
            }
        }
        return reply;
    }
}
