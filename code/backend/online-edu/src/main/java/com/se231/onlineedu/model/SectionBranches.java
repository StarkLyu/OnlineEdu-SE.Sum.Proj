package com.se231.onlineedu.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Section Branches Entity Class
 *
 * section branches under a section to divide the section into smaller parts.
 *
 * @author Zhe Li
 * @date 2019/07/15
 */
@Entity
public class SectionBranches {
    @EmbeddedId
    private SectionBranchesPrimaryKey sectionBranchesPrimaryKey;

    private String title;

    @OneToMany
    private List<Paper> papers;

    @OneToMany
    private List<Resource> resources;

    public SectionBranches() {
        papers = new ArrayList<>();
        resources = new ArrayList<>();
    }

    public SectionBranches(SectionBranchesPrimaryKey sectionBranchesPrimaryKey, String title, List<Paper> papers, List<Resource> resources) {
        this.sectionBranchesPrimaryKey = sectionBranchesPrimaryKey;
        this.title = title;
        this.papers = papers;
        this.resources = resources;
    }

    public SectionBranchesPrimaryKey getSectionBranchesPrimaryKey() {
        return sectionBranchesPrimaryKey;
    }

    public void setSectionBranchesPrimaryKey(SectionBranchesPrimaryKey sectionBranchesPrimaryKey) {
        this.sectionBranchesPrimaryKey = sectionBranchesPrimaryKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
