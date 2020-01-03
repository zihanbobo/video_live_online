package com.video.live.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

import static com.video.live.common.constant.EntityConstant.SCHEDULE_PREFIX_NAME;

/**
 * Spring 定时任务线程池配置类
 *
 * @Author: Deng Yunhu
 * @Date: 2020/1/3 9:55
 */
@Configuration
@EnableScheduling
public class TaskScheduleConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(8);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        taskScheduler.setThreadNamePrefix(SCHEDULE_PREFIX_NAME);
        return taskScheduler;
    }
}
