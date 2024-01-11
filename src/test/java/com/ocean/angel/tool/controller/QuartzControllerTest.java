package com.ocean.angel.tool.controller;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class QuartzControllerTest {

    static String JOB_CLASS = "com.ocean.angel.tool.job.MyJob";
    static String JOB_NAME = "myJob";
    static String JOB_CRON = "0/5 * * * * ?";

    @Test
    void startJob() {
        String url = "http://localhost:8090/quartz/job/start" + "?jobClass=" + JOB_CLASS + "&jobName=" + JOB_NAME + "&cron=" + JOB_CRON;
        String result = HttpUtil.get(url);
        log.info("result = {}", result);
    }

    @Test
    void pauseJob() {
        String url = "http://localhost:8090/quartz/job/pause" + "?jobName=" + JOB_NAME;
        String result = HttpUtil.get(url);
        log.info("result = {}", result);
    }

    @Test
    void resumeJob() {
        String url = "http://localhost:8090/quartz/job/resume" + "?jobName=" + JOB_NAME;
        String result = HttpUtil.get(url);
        log.info("result = {}", result);
    }

    @Test
    void runOneJob() {
        String url = "http://localhost:8090/quartz/job/run" + "?jobName=" + JOB_NAME;
        String result = HttpUtil.get(url);
        log.info("result = {}", result);
    }

    @Test
    void deleteJob() {
        String url = "http://localhost:8090/quartz/job/delete" + "?jobName=" + JOB_NAME;
        String result = HttpUtil.get(url);
        log.info("result = {}", result);

    }

    @Test
    void updateJob() {
        String url = "http://localhost:8090/quartz/job/update" + "?jobName=" + JOB_NAME + "&cron=" + JOB_CRON;
        String result = HttpUtil.get(url);
        log.info("result = {}", result);
    }
}
