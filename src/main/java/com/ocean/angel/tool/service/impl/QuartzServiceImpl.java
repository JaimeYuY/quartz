package com.ocean.angel.tool.service.impl;

import com.ocean.angel.tool.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

/**
 * @desc:
 * @author: jaime.yu
 * @time: 2022/4/23 13:46
 */
@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    private String JOB_GROUP_SUFFIX = "Group";
    private String JOB_TRIGGER_SUFFIX = "Trigger";
    private String JOB_TRIGGER_GROUP_SUFFIX = "TriggerGroup";

    @Override
    public void startJob(String jobClass, String jobName, String cron) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            // 启动scheduler
            scheduler.start();

            // 创建Job的JobDetail实例，并设置name/group
            Class<? extends Job> myJobClass = (Class<? extends Job>) Class.forName(jobClass);
            JobDetail jobDetail = JobBuilder.newJob(myJobClass)
                    .withIdentity(jobName, jobName + JOB_GROUP_SUFFIX)
                    .build();

            // 创建Trigger触发器设置使用cronSchedule方式调度
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobName + JOB_TRIGGER_SUFFIX, jobName + JOB_TRIGGER_GROUP_SUFFIX)
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();

            // 注册JobDetail实例到scheduler以及使用对应的Trigger触发时机
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            log.error("QuartzServiceImpl start() error, {}", e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            log.error("QuartzServiceImpl start() error, {}", e.getMessage(), e);
        }
    }

    @Override
    public void pauseJob(String jobName) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = new JobKey(jobName, jobName + JOB_GROUP_SUFFIX);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzServiceImpl pauseJob() error, {}", e.getMessage(), e);
        }
    }

    @Override
    public void resumeJob(String jobName) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = new JobKey(jobName, jobName + JOB_GROUP_SUFFIX);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzServiceImpl resumeJob() error, {}", e.getMessage(), e);
        }
    }

    @Override
    public void runOneJob(String jobName) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = new JobKey(jobName, jobName + JOB_GROUP_SUFFIX);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzServiceImpl runOneJob(), {}", e.getMessage(), e);
        }
    }

    @Override
    public void deleteJob(String jobName) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = new JobKey(jobName, jobName + JOB_GROUP_SUFFIX);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzServiceImpl deleteJob() error, {}", e.getMessage(), e);
        }
    }

    @Override
    public void updateJob(String jobName, String cron) {
        try {

            Scheduler scheduler = schedulerFactory.getScheduler();

            //获取到对应任务的触发器
            TriggerKey triggerKey = new TriggerKey(jobName + JOB_TRIGGER_SUFFIX, jobName + JOB_TRIGGER_GROUP_SUFFIX);

            //设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

            //重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("QuartzServiceImpl deleteJob(), {}", e.getMessage(), e);
        }
    }
}
