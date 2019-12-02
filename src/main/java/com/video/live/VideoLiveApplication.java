package com.video.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class VideoLiveApplication {
    private static final Logger logger = LoggerFactory.getLogger(VideoLiveApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(VideoLiveApplication.class, args);
    }

}
