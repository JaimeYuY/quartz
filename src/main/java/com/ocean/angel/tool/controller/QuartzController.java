package com.ocean.angel.tool.controller;

import com.ocean.angel.tool.common.ResultBean;
import com.ocean.angel.tool.service.QuartzService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * Quartz 操作 Controller
 */
@RestController
@RequestMapping("quartz")
public class QuartzController {

    @Resource
    private QuartzService quartzService;

    /**
     * 开启定时任务
     * @param jobClass
     * @param jobName
     * @param cron
     * @return
     */
    @GetMapping("job/start")
    public ResultBean startJob(String jobClass, String jobName, String cron) {
        if(null == jobClass) {
            jobClass = "com.ocean.angel.tool.job.MyJob";
            jobName = "myJob";
            cron = "0/5 * * * * ?";
        }
        quartzService.startJob(jobClass, jobName, cron);
        return ResultBean.success();
    }

    /**
     * 暂停定时任务
     * @param jobName
     * @return
     */
    @GetMapping("job/pause")
    public ResultBean pauseJob(String jobName) {
        if(null == jobName) {
            jobName = "myJob";
        }
        quartzService.pauseJob(jobName);
        return ResultBean.success();
    }

    /**
     * 恢复定时任务
     * @param jobName
     * @return
     */
    @GetMapping("job/resume")
    public ResultBean resumeJob(String jobName) {
        if(null == jobName) {
            jobName = "myJob";
        }
        quartzService.resumeJob(jobName);
        return ResultBean.success();
    }

    /**
     * 立即执行一次任务
     * @param jobName
     * @return
     */
    @GetMapping("job/run")
    public ResultBean runOneJob(String jobName) {
        if(null == jobName) {
            jobName = "myJob";
        }
        quartzService.runOneJob(jobName);
        return ResultBean.success();
    }

    /**
     * 删除任务
     * @param jobName
     * @return
     */
    @GetMapping("job/delete")
    public ResultBean deleteJob(String jobName) {
        if(null == jobName) {
            jobName = "myJob";
        }
        quartzService.deleteJob(jobName);
        return ResultBean.success();
    }

    /**
     * 更新任务
     * @param jobName
     * @param cron
     * @return
     */
    @GetMapping("job/update")
    public ResultBean updateJob(String jobName, String cron) {
        if(null == jobName) {
            jobName = "myJob";
        }
        quartzService.updateJob(jobName, cron);
        return ResultBean.success();
    }

}
