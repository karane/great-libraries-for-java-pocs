package org.karane;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        // First job
        JobDetail job1 = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("message", "Hello from Job 1!")
                .build();

        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job1, trigger1);

        // Second job
        JobDetail job2 = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job2", "group1")
                .usingJobData("message", "Hello from Job 2!")
                .build();

        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job2, trigger2);

        // Third job (replaced with GoodbyeJob)
        JobDetail job3 = JobBuilder.newJob(GoodbyeJob.class)
                .withIdentity("job3", "group1")
                .build();

        Trigger trigger3 = TriggerBuilder.newTrigger()
                .withIdentity("trigger3", "group1")
                .startAt(DateBuilder.futureDate(30, DateBuilder.IntervalUnit.SECOND)) // Start after 30 seconds
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(15)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job3, trigger3);

        // Shutdown hook to gracefully shutdown the scheduler
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                scheduler.shutdown(true);
                System.out.println("Scheduler shut down.");
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }));
    }
}
