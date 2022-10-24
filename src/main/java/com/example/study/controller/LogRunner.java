package com.example.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class LogRunner implements ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(LogRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("");
        logger.info("==================================");
        logger.info("v3");
        logger.info("study seoyoung's server");
        logger.info("ZonedDateTime: " + ZonedDateTime.now());
        logger.info("==================================");
        logger.info("");
    }
}
