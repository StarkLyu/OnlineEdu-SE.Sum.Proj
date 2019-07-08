package com.se231.onlineedu.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @PatchMapping("/{id}/avatar")
    @PreAuthorize("#id == authentication.principal.id")
    public String patchAvatar(@PathVariable Long id, @RequestParam(value = "avatar") MultipartFile multipartFile) throws IOException {
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String fileName = "/home/liu/nginx/online-edu/" + id + "-avatar/" + id + "-avatar" + suffix;
        File file = new File(fileName);

        if(file.getParentFile().exists()){
            FileUtils.cleanDirectory(file.getParentFile());
        } else {
            file.getParentFile().mkdir();
        }
        file.createNewFile();
        multipartFile.transferTo(file);

        return fileName;
    }
}
