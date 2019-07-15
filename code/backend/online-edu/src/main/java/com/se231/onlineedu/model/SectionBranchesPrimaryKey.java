package com.se231.onlineedu.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Section Branches' Primary Key
 *
 * a section branch should be identified by a section and a branch number.
 *
 * @author Zhe Li
 * @date 2019/07/15
 */
@Embeddable
public class SectionBranchesPrimaryKey implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ManyToOne
    private Section section;

    private int branchNo;

    public SectionBranchesPrimaryKey(Section section, int branchNo) {
        this.section = section;
        this.branchNo = branchNo;
    }

    public SectionBranchesPrimaryKey() {
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }
}
