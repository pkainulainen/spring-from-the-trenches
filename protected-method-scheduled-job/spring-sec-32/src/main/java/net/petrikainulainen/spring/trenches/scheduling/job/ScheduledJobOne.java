package net.petrikainulainen.spring.trenches.scheduling.job;

import net.petrikainulainen.spring.trenches.scheduling.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Petri Kainulainen
 */
@Component
public class ScheduledJobOne {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJobOne.class);

    private final MessageService messageService;

    @Autowired
    public ScheduledJobOne(MessageService messageService) {
        this.messageService = messageService;
    }

    @Scheduled(cron = "${scheduling.job.cron}")
    public void run() {
        LOGGER.debug("Starting scheduled job 1.");

        String message = messageService.getMessage();
        LOGGER.debug("Received message 1: {}", message);

        LOGGER.debug("Scheduled job 1 is finished.");
    }
}
