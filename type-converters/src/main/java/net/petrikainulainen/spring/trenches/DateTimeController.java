package net.petrikainulainen.spring.trenches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Petri Kainulainen
 */
@RestController
@RequestMapping("/api/datetime/")
final class DateTimeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeController.class);

    private final DateTimeService dateTimeService;

    @Autowired
    DateTimeController(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @RequestMapping(value = "date", method = RequestMethod.POST)
    public void processDate(@RequestParam("date") LocalDate date) {
        LOGGER.info("Processing date: {}", date);
        dateTimeService.processDate(date);
    }

    @RequestMapping(value = "datetime", method = RequestMethod.POST)
    public void processDateTime(@RequestParam("datetime") LocalDateTime dateAndTime) {
        LOGGER.info("Processing date and time: {}", dateAndTime);
        dateTimeService.processDateAndTime(dateAndTime);
    }
}
