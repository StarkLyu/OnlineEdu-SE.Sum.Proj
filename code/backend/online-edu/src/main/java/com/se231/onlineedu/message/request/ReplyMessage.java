package com.se231.onlineedu.message.request;

import com.se231.onlineedu.model.Reply;

import java.util.List;

public class ReplyMessage {
    private List<Integer> path;
    private Reply reply;

    public ReplyMessage(List<Integer> path, Reply reply) {
        this.path = path;
        this.reply = reply;
    }

    public List<Integer> getPath() {
        return path;
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
