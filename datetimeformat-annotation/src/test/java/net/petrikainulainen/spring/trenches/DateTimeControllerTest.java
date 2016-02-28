package net.petrikainulainen.spring.trenches;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static info.solidsoft.mockito.java8.AssertionMatcher.assertArg;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Petri Kainulainen
 */
@RunWith(NestedRunner.class)
public class DateTimeControllerTest {

    private DateTimeService dateTimeService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        dateTimeService = mock(DateTimeService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new DateTimeController(dateTimeService))
                .build();
    }

    public class ProcessDate {
        
        private final String DATE = "2015-09-26";
        private final String REQUEST_PARAM_DATE = "date";

        @Test
        public void shouldReturnResponseStatusOk() throws Exception {
            mockMvc.perform(post("/api/datetime/date")
                    .param(REQUEST_PARAM_DATE, DATE)

            )
                    .andExpect(status().isOk());
        }

        @Test
        public void shouldProcessDate() throws Exception {
            mockMvc.perform(post("/api/datetime/date")
                            .param(REQUEST_PARAM_DATE, DATE)

            );

            verify(dateTimeService, times(1)).processDate(assertArg(
                    date -> assertTrue(date.equals(LocalDate.of(2015, 9, 26)))
            ));
        }
    }

    public class ProcessDateTime {

        private final String DATE_TIME = "2015-09-26T01:30:00.000";
        private final String REQUEST_PARAM_DATE_TIME = "datetime";

        @Test
        public void shouldReturnResponseStatusOk() throws Exception {
            mockMvc.perform(post("/api/datetime/datetime")
                            .param(REQUEST_PARAM_DATE_TIME, DATE_TIME)

            )
                    .andExpect(status().isOk());
        }

        @Test
        public void shouldProcessDateTime() throws Exception {
            mockMvc.perform(post("/api/datetime/datetime")
                            .param(REQUEST_PARAM_DATE_TIME, DATE_TIME)

            );

            verify(dateTimeService, times(1)).processDateAndTime(assertArg(
                    dateAndTime -> assertTrue(dateAndTime.equals(LocalDateTime.of(2015, 9, 26, 1, 30)))
            ));
        }
    }
}
