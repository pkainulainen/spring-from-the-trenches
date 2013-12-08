package net.petrikainulainen.spring.trenches.scheduling.job;

import net.petrikainulainen.spring.trenches.scheduling.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJobTwo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJobOne.class);

    private final MessageService messageService;

    @Autowired
    public ScheduledJobTwo(MessageService messageService) {
        this.messageService = messageService;
    }

    @Scheduled(cron = "${scheduling.job.cron}")
    public void run() {
        LOGGER.debug("Starting scheduled job 2.");

        String message = messageService.getMessage();
        LOGGER.debug("Received message 2: {}", message);

        LOGGER.debug("Scheduled job 2 is finished.");
    }
}
