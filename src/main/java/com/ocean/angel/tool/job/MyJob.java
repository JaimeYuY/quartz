package com.ocean.angel.tool.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import java.util.Date;

/**
 * 自定义Job
 */
@Slf4j
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        TriggerKey triggerKey = context.getTrigger().getKey();
        log.info("jobKey = {}, triggerKey = {}, time = {}", jobKey, triggerKey, new Date());
    }
}
