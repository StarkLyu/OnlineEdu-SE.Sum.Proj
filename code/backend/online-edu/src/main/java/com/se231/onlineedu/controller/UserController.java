package com.se231.onlineedu.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@PropertySource(value={"classpath:user.properties"})
public class UserController {

    @Value("${app.nginx.path}")
    private String nginxPath;

    @Value("${app.file.limit}")
    private Long limit;

    static String fileExtension = ".jpg,.jpeg,.png,.svg,.tif";


    @PatchMapping("/{id}/avatar")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<String> patchAvatar(@PathVariable Long id, @RequestParam(value = "avatar") MultipartFile multipartFile) throws IOException {
        if(multipartFile.getSize() > limit){
            return ResponseEntity.badRequest().body("exceeded max size");
        }

        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

        if(!fileExtension.contains(suffix)){
            return ResponseEntity.badRequest().body("file format not supported");
        }
        String fileName = nginxPath + id + "-avatar/" + id + "-avatar" + suffix;
        File file = new File(fileName);

        if(file.getParentFile().exists()){
            FileUtils.cleanDirectory(file.getParentFile());
        } else {
            file.getParentFile().mkdir();
        }
        file.createNewFile();
        multipartFile.transferTo(file);

        return ResponseEntity.ok(fileName);
    }
}
