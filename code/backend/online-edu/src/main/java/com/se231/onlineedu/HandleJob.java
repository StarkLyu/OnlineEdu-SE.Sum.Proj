package com.se231.onlineedu;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.se231.onlineedu.service.CourseService;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zhe Li
 * @date 2019/07/17
 */
@Component
public class HandleJob implements Job {
    @Autowired
    private CourseService courseService;

    public static HandleJob handleJob;

    @PostConstruct
    public void init(){
        handleJob=this;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail detail = context.getJobDetail();
        String state = detail.getJobDataMap().getString("state");
        Long courseId = detail.getJobDataMap().getLongValue("courseId");
        handleJob.courseService.setState(courseId,state);
    }
}
