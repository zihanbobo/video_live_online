package com.video.live.job.impl;

import com.video.live.web.controller.VideoController;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Deng Yunhu
 * @Date: 2020/1/3 11:24
 */
@Component
@DisallowConcurrentExecution
public class TestJobImpl implements Job {

    @Autowired
    private VideoController videoController;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String name = context.getMergedJobDataMap().getString("name");
        videoController.testSche(name);
    }
}
