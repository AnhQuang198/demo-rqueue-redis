package com.example.demomsgqueueredis.services;

import com.example.demomsgqueueredis.dtos.Email;
import com.github.sonus21.rqueue.annotation.RqueueListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class TestService {

    @RqueueListener(
            value = "test-string-queue_critical",
            concurrency = "60")
    public void queueConsumer(Email email) {
        log.info("Message UUID (Email): " + email);
    }

    @RqueueListener(
            value = "test-number-queue_critical",
            concurrency = "60")
    public void queueConsumerQueueNumber(String msg) {
        log.info("Message UUID (Number): " + msg);
    }
}
