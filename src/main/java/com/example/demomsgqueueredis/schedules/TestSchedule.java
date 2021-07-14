package com.example.demomsgqueueredis.schedules;

import com.example.demomsgqueueredis.constants.JobPriority;
import com.example.demomsgqueueredis.dtos.Email;
import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import com.github.sonus21.rqueue.core.RqueueMessageSender;
import com.github.sonus21.rqueue.exception.QueueDoesNotExist;
import com.github.sonus21.rqueue.models.event.RqueueBootstrapEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.demomsgqueueredis.queue.Queue.TEST_NUMBER_QUEUE;
import static com.example.demomsgqueueredis.queue.Queue.TEST_STRING_QUEUE;

@Component
@Log4j2
public class TestSchedule {
    @Autowired
    private RqueueMessageEnqueuer rqueueMessageEnqueuer;

    @EventListener
    public void loadAuctions(RqueueBootstrapEvent event) {
        if (event.isStart()) {
            log.warn("[ SCHEDULER ]");
            putMessageQueue();
        }
    }

    @Scheduled(fixedDelay = 3000, initialDelay = 3000)
    private void putMessageQueue() {
        log.info("=====Run schedule 1 =====");
        UUID uuid = UUID.randomUUID();
        Email email = new Email(uuid.toString(), "new content");
        rqueueMessageEnqueuer.enqueueWithPriority(TEST_STRING_QUEUE, JobPriority.CRITICAL, email);
    }


    //fixedDelay: sau khi chay func thi sau time(ms) se chay tiep
    //initialDelay: func se duoc chay sau time(ms)
    @Scheduled(fixedDelay = 3000, initialDelay = 3000)
    private void puMessageQueue2() {
        log.info("=====Run schedule 2 =====");
        rqueueMessageEnqueuer.enqueueWithPriority(TEST_NUMBER_QUEUE, JobPriority.CRITICAL, 1);
    }
}
