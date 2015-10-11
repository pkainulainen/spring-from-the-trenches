package net.petrikainulainen.spring.trenches;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Petri Kainulainen
 */
@RunWith(NestedRunner.class)
public class LocalDateConverterTest {

    public class Convert {

        private final String ISO_DATE_FORMAT = "yyyy-MM-dd";

        private LocalDateConverter converter;

        @Before
        public void createConverterThatUsesIsoDateFormat() {
            converter = new LocalDateConverter(ISO_DATE_FORMAT);
        }

        public class WhenSourceStringIsEmpty {

            @Test
            public void shouldReturnNull() {
                LocalDate date = converter.convert("");
                assertThat(date).isNull();
            }
        }

        public class WhenSourceStringIsNull {

            @Test
            public void shouldReturnNull() {
                LocalDate date = converter.convert(null);
                assertThat(date).isNull();
            }
        }

        public class WhenSourceStringUsesIsoDateFormat {

            private final String DATE_STRING = "2015-10-13";
            private final LocalDate EXPECTED_DATE = LocalDate.of(2015, 10, 13);

            @Test
            public void shouldReturnExpectedDate() {
                LocalDate date = converter.convert(DATE_STRING);
                assertThat(date).isEqualTo(EXPECTED_DATE);
            }
        }

        public class WhenSourceStringDoesNotUseIsoDateFormat {

            private final String DATE_STRING = "13.10.2015";

            @Test(expected = DateTimeParseException.class)
            public void shouldThrowException() {
                converter.convert(DATE_STRING);
            }
        }
    }
}
