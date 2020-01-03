package com.video.live.job;

import cn.hutool.core.util.StrUtil;
import com.video.live.common.exception.ServerException;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Map;

/**
 * Quartz定时任务服务支持类
 *
 * @Author: Deng Yunhu
 * @Date: 2020/1/3 10:33
 */
@Configuration
@SuppressWarnings("all")
public class TaskJobSupport {

    @Autowired
    private Scheduler scheduler;

    public void addJob(Class<? extends Job> jobClazz, String cronExpression, String jobName, String jobGroup) {
        addJob(jobClazz, cronExpression, jobName, jobGroup, Collections.EMPTY_MAP);
    }

    public void addJob(Class<? extends Job> jobClazz, String cronExpression, String jobName, String jobGroup, Map param) {
        try {
            scheduler.start();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobDetail jobDetail = JobBuilder.newJob(jobClazz)
                    .withIdentity(jobKey)
                    .build();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression.trim());
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .startNow()
                    .withSchedule(cronScheduleBuilder)
                    .build();
            cronTrigger.getJobDataMap().putAll(param);
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (SchedulerException e) {
            throw new ServerException(StrUtil.format("定时任务{}-{}执行失败", jobGroup, jobName));
        }
    }

    public void removeJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new ServerException(StrUtil.format("定时任务{}-{}移除失败", jobGroup, jobName));
        }
    }

    public void pauseJob(String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new ServerException(StrUtil.format("定时任务{}-{}暂停失败", jobGroup, jobName));
        }
    }

    public void resumeJob(String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw new ServerException(StrUtil.format("定时任务{}-{}恢复失败", jobGroup, jobName));
        }
    }

    public void updateJob(String jobName, String jobGroup, String cronExpression) {
        updateJob(jobName, jobGroup, cronExpression, Collections.EMPTY_MAP);
    }

    public void updateJob(String jobName, String jobGroup, String cronExpression, Map param) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(cronScheduleBuilder)
                    .build();
            trigger.getJobDataMap().putAll(param);
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            throw new ServerException(StrUtil.format("定时任务{}-{}-{}更新失败失败", jobGroup, jobName,cronExpression));
        }
    }
}
