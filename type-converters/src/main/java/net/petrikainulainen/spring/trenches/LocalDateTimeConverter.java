package net.petrikainulainen.spring.trenches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This type converter class converts {@code String} objects into Java 8 {@code LocalDateTime} objects.
 *
 * @author Petri Kainulainen
 */
public final class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateTimeConverter.class);

    private final String dateFormat;
    private final DateTimeFormatter formatter;

    /**
     * Creates a new LocalDateConverter that can convert {@code String} objects into Java 8 {@code LocalDateTime} objects.
     *
     * @param dateFormat The used date format. This format must use the syntax supported by the
     *                   <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">
     *                       {@code DateTimeFormat}
     *                   </a> class.
     */
    public LocalDateTimeConverter(String dateFormat) {
        this.dateFormat = dateFormat;
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public LocalDateTime convert(String source) {
        LOGGER.trace("Converting the string: {} into a LocalDateTime object by using date format: {}.",
                source,
                dateFormat
        );

        if (source == null) {
            LOGGER.trace("The source string is null. Returning null.");
            return null;
        }

        LocalDateTime dateAndTime = LocalDateTime.parse(source, formatter);
        LOGGER.trace("Converted the string: {} into a LocalDateTime: {}",
                source,
                dateAndTime
        );

        return dateAndTime;
    }
}
