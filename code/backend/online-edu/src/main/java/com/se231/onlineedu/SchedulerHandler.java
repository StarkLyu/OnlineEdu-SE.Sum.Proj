package com.se231.onlineedu;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerHandler {
    private static int times;
    public static void setCourseState(Long courseId, String state, Date triggerDate)throws SchedulerException {
        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //定义一个触发器
        Trigger trigger = newTrigger().withIdentity("trigger"+times, "group1") //定义名称和所属的组
                .startNow()
                .startAt(triggerDate)
                .build();

        //定义一个JobDetail
        JobDetail job = newJob(HandleJob.class) //指定干活的类MailJob
                .withIdentity("course"+(times++), "course") //定义任务名称和分组
                .usingJobData("courseId", courseId) //定义属性
                .usingJobData("state",state)
                .build();

        //调度加入这个job
        scheduler.scheduleJob(job, trigger);

        //启动
        scheduler.start();

    }
}
