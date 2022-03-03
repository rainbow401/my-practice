package com.practice.job;

import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.time.LocalDateTime;

public class HelloJob implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("---------------------------------------");
        Object tv1 = context.getTrigger().getJobDataMap().get("t1");
        Object tv2 = context.getTrigger().getJobDataMap().get("t2");
        Object jv1 = context.getJobDetail().getJobDataMap().get("jd1");
        Object jv2 = context.getJobDetail().getJobDataMap().get("jd2");
        Object sh = context.getScheduler().getContext().get("skey");
        Object sv = null;
        try {
            sv = context.getScheduler().getContext().get("skey");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println(tv1+":"+tv2);
        System.out.println(jv1+":"+jv2);
        System.out.println(sv);
        System.out.println("hello:"+ LocalDateTime.now());
    }

}