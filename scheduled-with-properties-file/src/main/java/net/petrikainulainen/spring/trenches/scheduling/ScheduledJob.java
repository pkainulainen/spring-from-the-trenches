package net.petrikainulainen.spring.trenches.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Petri Kainulainen
 */
@Component
public class ScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);

    @Scheduled(cron = "${scheduling.job.cron}")
    public void run() {
        LOGGER.debug("run()");
    }
}
