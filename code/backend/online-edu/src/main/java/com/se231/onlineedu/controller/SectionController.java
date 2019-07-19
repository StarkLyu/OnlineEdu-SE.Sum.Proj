package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.TitleAndDes;
import com.se231.onlineedu.model.Section;
import com.se231.onlineedu.service.SectionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhe Li
 * @date 2019/07/19
 */
@Api
@RestController
@RequestMapping("api/courses/{courseId}/section")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @PostMapping("/create")
    public Section createSection(@PathVariable("courseId")Long courseId,
                                 @Valid @RequestBody TitleAndDes form){
        return sectionService.createSection(courseId, form);
    }
}
