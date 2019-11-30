package com.video.live;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.video.live.entity")
public class VideoLiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoLiveApplication.class, args);
    }

}
