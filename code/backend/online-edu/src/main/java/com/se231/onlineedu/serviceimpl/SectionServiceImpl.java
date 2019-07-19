package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.message.request.TitleAndDes;
import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.model.Section;
import com.se231.onlineedu.model.SectionPrimaryKey;
import com.se231.onlineedu.repository.CourseRepository;
import com.se231.onlineedu.repository.SectionRepository;
import com.se231.onlineedu.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Section Service Implementation Class
 *
 * Contains service logic related to section.
 *
 * @author Zhe Li
 * @date 2019/07/19
 */
@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public Section createSection(Long courseId, TitleAndDes form) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("课程不存在"));
        Section section = new Section();
        section.setTitle(form.getTitle());
        section.setDescription(form.getDescription());
        int secNo = sectionRepository.currentSec(courseId).orElse(0);
        SectionPrimaryKey sectionPrimaryKey = new SectionPrimaryKey(course,secNo+1);
        section.setSectionPrimaryKey(sectionPrimaryKey);
        return sectionRepository.save(section);
    }
}
