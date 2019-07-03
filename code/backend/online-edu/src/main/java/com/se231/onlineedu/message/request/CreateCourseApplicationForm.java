package com.se231.onlineedu.message.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Create Course form class
 *
 * A form for teaching admin to create a new course.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */
public class CreateCourseApplicationForm {
    @NotBlank
    @Size(min = 3,max = 30)
    private String title;

    @Size(max=1000)
    private String description;

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
}
