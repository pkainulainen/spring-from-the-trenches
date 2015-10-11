package net.petrikainulainen.spring.trenches;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Petri Kainulainen
 */
@RunWith(NestedRunner.class)
public class LocalDateTimeConverterTest {

    public class Convert {

        private final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

        private LocalDateTimeConverter converter;

        @Before
        public void createConverterThatUsesIsoDateTimeFormat() {
            converter = new LocalDateTimeConverter(ISO_DATE_TIME_FORMAT);
        }

        public class WhenSourceStringIsEmpty {

            @Test
            public void shouldReturnNull() {
                LocalDateTime dateAndTime = converter.convert("");
                assertThat(dateAndTime).isNull();
            }
        }

        public class WhenSourceStringIsNull {

            @Test
            public void shouldReturnNull() {
                LocalDateTime dateAndTime = converter.convert(null);
                assertThat(dateAndTime).isNull();
            }
        }

        public class WhenSourceStringUsesIsoDateFormat {

            private final String DATE_TIME_STRING = "2015-10-13T01:30:00.000";
            private final LocalDateTime EXPECTED_DATE_TIME = LocalDateTime.of(2015, 10, 13, 1, 30);

            @Test
            public void shouldReturnExpectedDate() {
                LocalDateTime dateAndTime = converter.convert(DATE_TIME_STRING);
                assertThat(dateAndTime).isEqualTo(EXPECTED_DATE_TIME);
            }
        }

        public class WhenSourceStringDoesNotUseIsoDateFormat {

            private final String DATE_TIME_STRING = "13.10.2015 01:30:30.000";

            @Test(expected = DateTimeParseException.class)
            public void shouldThrowException() {
                converter.convert(DATE_TIME_STRING);
            }
        }
    }
}
