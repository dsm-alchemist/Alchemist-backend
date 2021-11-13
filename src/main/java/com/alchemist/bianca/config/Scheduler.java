package com.alchemist.bianca.config;

import com.alchemist.bianca.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {

    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void setIsStop() {
        userRepository.bulkUpdate();
    }
}
