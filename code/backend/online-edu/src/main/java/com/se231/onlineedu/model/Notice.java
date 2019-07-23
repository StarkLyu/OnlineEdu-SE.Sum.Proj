package com.se231.onlineedu.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author yuxuanLiu
 * @date 2019/07/22
 */
@Entity
public class Notice {
    @EmbeddedId
    private NoticePrimaryKey noticePrimaryKey;

    public NoticePrimaryKey getNoticePrimaryKey() {
        return noticePrimaryKey;
    }

    public void setNoticePrimaryKey(NoticePrimaryKey noticePrimaryKey) {
        this.noticePrimaryKey = noticePrimaryKey;
    }

    private Date issueDate = new Date();

    private String content;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
