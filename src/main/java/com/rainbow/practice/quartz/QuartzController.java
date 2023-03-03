package com.rainbow.practice.quartz;

import com.rainbow.practice.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2022/3/2 5:42 下午
 *
 * @author yanzhihao
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @RequestMapping("/init")
    public void initQuartz() throws SchedulerException {

        //创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("skey", "svalue");

        //创建一个Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("t1", "tValue1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();
        trigger.getJobDataMap().put("t2", "tValue2");

        //创建一个job
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .usingJobData("jd1", "jdValue1")
                .withIdentity("myjob", "mygroup").build();
        job.getJobDataMap().put("jd2", "jdValue2");

        //注册trigger并启动scheduler以执行HelloJob
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
    }
}
