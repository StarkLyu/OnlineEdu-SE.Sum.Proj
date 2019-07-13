package com.se231.onlineedu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reply {

    private String content;
    private List<Reply> replies = new ArrayList<>();
    private Date createdAt = new Date();

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    private List<String> imageUrls = new ArrayList<>();

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private int likes = 0;
    private Long userId;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
