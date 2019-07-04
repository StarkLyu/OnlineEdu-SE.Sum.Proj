package com.se231.onlineedu.message.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Create Course form class
 *  *
 *  * A form for teaching admin to create a new course.
 *  *
 *  * @author Zhe Li
 *  *
 *  * @date 2019/7/4
 */
public class CreateCourseApplicationForm {
    @NotNull
    @FutureOrPresent
    private Date startDate;

    @NotNull
    @Future
    private Date endDate;

    @NotNull
    private Long coursePrototypeId;
}
