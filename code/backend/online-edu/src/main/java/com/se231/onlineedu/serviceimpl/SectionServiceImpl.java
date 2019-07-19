package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.exception.NotMatchException;
import com.se231.onlineedu.message.request.TitleAndDes;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.*;
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

    @Autowired
    SectionBranchRepository sectionBranchRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    ResourceRepository resourceRepository;

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

    @Override
    public SectionBranches createBranch(Long courseId, int secNo,String title) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("课程不存在"));
        SectionPrimaryKey sectionPrimaryKey = new SectionPrimaryKey(course,secNo);
        Section section = sectionRepository.findById(sectionPrimaryKey).orElseThrow(()->new NotFoundException("章节不存在"));
        int branchNo = sectionBranchRepository.currentBranch(courseId,secNo).orElse(0);
        SectionBranchesPrimaryKey sectionBranchesPrimaryKey = new SectionBranchesPrimaryKey(section,branchNo+1);
        SectionBranches sectionBranches = new SectionBranches();
        sectionBranches.setTitle(title);
        sectionBranches.setSectionBranchesPrimaryKey(sectionBranchesPrimaryKey);
        return sectionBranchRepository.save(sectionBranches);
    }

    @Override
    public Section issuePaper(Long courseId, int secNo, int branchNo, Long paperId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("课程不存在"));
        Section section = sectionRepository.findById(new SectionPrimaryKey(course,secNo))
                .orElseThrow(()->new NotFoundException("No corresponding question"));
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(()->new NotFoundException("No corresponding paper"));
        if(!paper.getCourse().equals(course)){
            throw new NotMatchException("该试卷不在课程中");
        }
        section.getPapers().add(paper);
        SectionBranches sectionBranches = sectionBranchRepository.findById(new SectionBranchesPrimaryKey(section,branchNo))
                .orElseThrow(()->new NotFoundException("小节不存在"));
        sectionBranches.getPapers().add(paper);
        sectionBranchRepository.save(sectionBranches);
        return sectionRepository.save(section);
    }

    @Override
    public Section issueResource(Long courseId, int secNo, int branchNo, Long resourceId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("课程不存在"));
        Section section = sectionRepository.findById(new SectionPrimaryKey(course,secNo))
                .orElseThrow(()->new NotFoundException("No corresponding question"));
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(()->new NotFoundException("No corresponding paper"));
        section.getResources().add(resource);
        if(!course.getCoursePrototype().getResources().contains(resource)){
            throw new NotMatchException("该课程原型中无该资源");
        }
        SectionBranches sectionBranches = sectionBranchRepository.findById(new SectionBranchesPrimaryKey(section,branchNo))
                .orElseThrow(()->new NotFoundException("小节不存在"));
        sectionBranches.getResources().add(resource);
        sectionBranchRepository.save(sectionBranches);
        return sectionRepository.save(section);
    }
}
