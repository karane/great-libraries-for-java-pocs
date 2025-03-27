package org.karane;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String message = context.getJobDetail().getJobDataMap().getString("message");
        System.out.println(message + " Time: " + LocalTime.now());
    }
}