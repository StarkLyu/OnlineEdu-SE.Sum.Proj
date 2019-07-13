package com.se231.onlineedu.controller;


import com.alibaba.fastjson.JSONObject;
import com.se231.onlineedu.message.request.PathMessage;
import com.se231.onlineedu.message.request.ReplyMessage;
import com.se231.onlineedu.model.Forum;
import com.se231.onlineedu.model.Reply;
import com.se231.onlineedu.service.ForumService;
import com.se231.onlineedu.util.FileCheckUtil;
import com.se231.onlineedu.util.ImageWithInfo;
import com.se231.onlineedu.util.SaveFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/forums")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @Value("${app.file.limit}")
    private int limit;


    @PostMapping("/")
    public Forum updateForum(@RequestBody Forum forum) {
        return forumService.updateForum(forum);
    }

    @GetMapping("/{id}")
    public Forum getForum(@PathVariable String id) {
        return forumService.getForum(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> insertReply(@PathVariable String id, @RequestBody ReplyMessage replyMessage) {
        return forumService.insertReply(id, replyMessage);
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<?> insertImages(@PathVariable String id, @RequestParam("images") MultipartFile[] multipartFiles, @RequestParam("path") String pathString) throws IOException {
        PathMessage pathMessage = JSONObject.parseObject(pathString, PathMessage.class);
        Forum forum = forumService.getForum(id);
        Object forumOrReply = forumService.getReplyOrForum(forum, pathMessage);
        ImageWithInfo imageWithInfo = SaveFileUtil.saveImages(multipartFiles, limit);
        if (imageWithInfo.isHasError()) {
            return ResponseEntity.badRequest().body(imageWithInfo.getErrorMessage());
        }
        if(pathMessage.getPath().isEmpty()){
            ((Forum)forumOrReply).setImageUrls(imageWithInfo.getImagesUrls());
        } else {
            ((Reply)forumOrReply).setImageUrls(imageWithInfo.getImagesUrls());
        }
        return ResponseEntity.ok(forumService.updateForum(forum));
    }

    @DeleteMapping("{id}/images/{filename}")
    public boolean deleteImage(@PathVariable String id, @PathVariable String filename, @RequestParam("path") String pathString) throws IOException {
        PathMessage pathMessage = JSONObject.parseObject(pathString, PathMessage.class);
        Forum forum = forumService.getForum(id);
        Object forumOrReply = forumService.getReplyOrForum(forum, pathMessage);
        if(pathMessage.getPath().isEmpty()){
            ((Forum)forumOrReply).getImageUrls().remove(filename);
        }else {
            ((Reply)forumOrReply).getImageUrls().remove(filename);
        }
        forumService.updateForum(forum);
        return SaveFileUtil.deleteImage(filename);
    }
}
