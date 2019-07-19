package com.se231.onlineedu.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SectionRepository Entity Class
 *
 * a section is a teaching module in the convenience of teacher to manage resource.
 *
 * @author Yuxuan Liu
 *
 * @date 2019/7/4
 */
@Entity
public class Section {
    @EmbeddedId
    private SectionPrimaryKey sectionPrimaryKey;

    private String title;

    private String description;

    @OneToMany
    private List<Paper> papers;

    @OneToMany
    private List<Resource> resources;

    public Section() {
        resources=new ArrayList<>();
        papers=new ArrayList<>();
    }

    public SectionPrimaryKey getSectionPrimaryKey() {
        return sectionPrimaryKey;
    }

    public void setSectionPrimaryKey(SectionPrimaryKey sectionPrimaryKey) {
        this.sectionPrimaryKey = sectionPrimaryKey;
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
