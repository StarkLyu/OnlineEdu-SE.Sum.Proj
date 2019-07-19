package com.se231.onlineedu.controller;


import com.alibaba.fastjson.JSONObject;
import com.se231.onlineedu.message.request.PathMessage;
import com.se231.onlineedu.message.request.ReplyMessage;
import com.se231.onlineedu.model.Forum;
import com.se231.onlineedu.model.Reply;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.*;
import com.se231.onlineedu.util.ImageWithInfo;
import com.se231.onlineedu.util.SaveFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author liu
 * @date 2017/07/11
 */
@RestController
@RequestMapping("/api")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @Autowired
    private DiscernSensitiveWordsService discernSensitiveWordsService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private CourseService courseService;

    @Value("${app.file.limit}")
    private int limit;


    @PostMapping("/courses/{courseId}/sections/{secNo}/forums")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'TEACHING_ADMIN','SUPER_ADMIN')")
    public Forum updateForum(@RequestBody Forum forum, @PathVariable Long courseId, @PathVariable int secNo, @AuthenticationPrincipal UserPrinciple userPrinciple) {
        if(!discernSensitiveWordsService.discern(forum.getTitle()) || !discernSensitiveWordsService.discern(forum.getContent())){
            for(String email: courseService.getTeacherAssistantAndTeacherEmail(courseId)){
                emailSenderService.sendSensitiveWordsDetectedWords(email);
            }
        }
        forum.setCourseId(courseId);
        forum.setSecNo(secNo);
        forum.setUserId(userPrinciple.getId());
        return forumService.updateForum(forum);
    }

    @GetMapping("/courses/{courseId}/forums")
    public List<Forum> getForumsByCourse(@PathVariable Long courseId){
        return forumService.getForumsByCourse(courseId);
    }

    @GetMapping("/courses/{courseId}/sections/{secNo}/forums")
    public List<Forum> getForums(@PathVariable Long courseId, @PathVariable int secNo){
        return forumService.getForumsBySection(courseId, secNo);
    }


    @GetMapping("/forums/{id}")
    public Forum getForumById(@PathVariable String id) {
        return forumService.getForum(id);
    }

    @PostMapping("/forums/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'TEACHING_ADMIN','SUPER_ADMIN') and replyMessage.getReply().getUserId() == authentication.principal.id")
    public Forum insertReply(@PathVariable String id, @RequestBody ReplyMessage replyMessage) {
        return forumService.insertReply(id, replyMessage);
    }

    @PostMapping("/forums/{id}/images")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'TEACHING_ADMIN','SUPER_ADMIN')")
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

    @DeleteMapping("/forums/{id}/images/{filename}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'TEACHING_ADMIN','SUPER_ADMIN')")
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
