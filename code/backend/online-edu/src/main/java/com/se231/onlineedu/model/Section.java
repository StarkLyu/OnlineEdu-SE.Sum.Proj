package com.se231.onlineedu.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section {
    @EmbeddedId
    private SectionPrimaryKey sectionPrimaryKey;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private String title;

    private String description;

    @OneToMany
    private List<Paper> papers;

    @OneToMany
    private List<Resource> resources;

    public Section() {
    }

    public Section(SectionPrimaryKey sectionPrimaryKey, Course course, String title, String description, List<Paper> papers, List<Resource> resources) {
        this.sectionPrimaryKey = sectionPrimaryKey;
        this.course = course;
        this.title = title;
        this.description = description;
        this.papers = papers;
        this.resources = resources;
    }

    public SectionPrimaryKey getSectionPrimaryKey() {
        return sectionPrimaryKey;
    }

    public void setSectionPrimaryKey(SectionPrimaryKey sectionPrimaryKey) {
        this.sectionPrimaryKey = sectionPrimaryKey;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
