package net.petrikainulainen.spring.trenches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Petri Kainulainen
 */
@Service
class DateTimeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeService.class);

    void processDate(LocalDate date) {
        LOGGER.info("Processing date: {}", date);
    }

    void processDateAndTime(LocalDateTime dateAndTime) {
        LOGGER.info("Processing datetime: {}", dateAndTime);
    }
}
