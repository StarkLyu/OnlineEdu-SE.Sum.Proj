package com.se231.onlineedu.controller;


import com.se231.onlineedu.exception.FileFormatNotSupportException;
import com.se231.onlineedu.model.Resource;
import com.se231.onlineedu.model.ResourceType;
import com.se231.onlineedu.service.CoursePrototypeService;
import com.se231.onlineedu.util.FileCheckUtil;
import com.se231.onlineedu.util.SaveFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liu
 * @date 2019/07/11
 */
@RestController
@RequestMapping("/api/coursePrototypes/{courseProtoTypeId}/{resourceType}")
public class ResourceController {
    @Autowired
    public CoursePrototypeService coursePrototypeService;

    @PostMapping("/")
    public String uploadVideos(@RequestParam("resource")MultipartFile multipartFile, @PathVariable Long courseProtoTypeId, @PathVariable String resourceType) throws Exception {
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        if(FileCheckUtil.checkVideoTypeWrong(suffix)){
            throw new FileFormatNotSupportException("wrong format");
        }
        String url = SaveFileUtil.saveFile(multipartFile, suffix);
        Resource resource = new Resource(url, ResourceType.valueOf(resourceType.toUpperCase()));
        coursePrototypeService.saveResource(courseProtoTypeId, resource);
        return url;
    }
}
