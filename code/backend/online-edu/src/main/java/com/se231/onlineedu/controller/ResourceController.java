package com.se231.onlineedu.controller;


import com.se231.onlineedu.model.Resource;
import com.se231.onlineedu.model.ResourceType;
import com.se231.onlineedu.service.CoursePrototypeService;
import com.se231.onlineedu.util.FileCheckUtil;
import com.se231.onlineedu.util.SaveFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/coursePrototypes/{courseProtoTypeId}/{resourceType}")
public class ResourceController {
    @Autowired
    public CoursePrototypeService coursePrototypeService;

    @PostMapping("/")
    public ResponseEntity<String> uploadVideos(@RequestParam("resource")MultipartFile multipartFile, @PathVariable Long courseProtoTypeId, @PathVariable String resourceType) throws Exception {
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        if(FileCheckUtil.checkVideoTypeWrong(suffix)){
            return ResponseEntity.badRequest().body("wrong format");
        }
        String url = SaveFileUtil.saveFile(multipartFile, suffix);
        Resource resource = new Resource(url, ResourceType.valueOf(resourceType.toUpperCase()));
        coursePrototypeService.saveResource(courseProtoTypeId, resource);
        return ResponseEntity.ok(url);
    }
}
