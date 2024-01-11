package com.ocean.angel.tool.service;

/**
 * Quartz 操作
 */
public interface QuartzService {

    /**
     * @desc 开启定时任务
     *
     * @param jobClass
     * @param jobName
     * @param cron
     * @return void
     */
    void startJob(String jobClass, String jobName, String cron);

    /**
     * @desc 暂停定时任务
     *
     * @param jobName
     * @return void
     */
    void pauseJob(String jobName);

    /**
     * @desc 恢复定时任务
     *
     * @param jobName
     * @return void
     */
    void resumeJob(String jobName);

    /**
     * @desc 立即执行一次定时任务
     *
     * @param jobName
     * @return void
     */
    void runOneJob(String jobName);

    /**
     * @desc 删除定时任务
     *
     * @param jobName
     * @return void
     */
    void deleteJob(String jobName);

    /**
     * @desc 更新定时任务
     *
     * @param jobName
     * @param cron
     * @return void
     */
    void updateJob(String jobName, String cron);
}
