package net.petrikainulainen.spring.trenches;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static info.solidsoft.mockito.java8.AssertionMatcher.assertArg;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Petri Kainulainen
 */
@RunWith(NestedRunner.class)
public class FooBarControllerTest {

    public class ProcessFooBar {

        private FooBarService fooBarService;

        private MockMvc mockMvc;

        @Before
        public void setUp() {
            fooBarService = mock(FooBarService.class);
            mockMvc = MockMvcBuilders.standaloneSetup(new FooBarController(fooBarService))
                    .setCustomArgumentResolvers(new FooBarHandlerMethodArgumentResolver())
                    .build();
        }

        @Test
        public void shouldReturnResponseStatusOk() throws Exception {
            mockMvc.perform(get("/test"))
                    .andExpect(status().isOk());
        }

        public class WhenRequestParametersAreNotSet {

            @Test
            public void shouldProcessFooBarWithDefaultBarValue() throws Exception {
                mockMvc.perform(get("/test"));

                verify(fooBarService, times(1)).processFooBar(assertArg(
                        fooBar -> assertThat(fooBar.getBar()).isEqualTo("defaultBar")
                ));
            }

            @Test
            public void shouldProcessFooBarWithDefaultFooValue() throws Exception {
                mockMvc.perform(get("/test"));

                verify(fooBarService, times(1)).processFooBar(assertArg(
                        fooBar -> assertThat(fooBar.getFoo()).isEqualTo("defaultFoo")
                ));
            }
        }

        public class WhenRequestParametersAreSet {

            private final String BAR = "bar";
            private final String FOO = "foo";

            private final String REQUEST_PARAM_BAR = "bar";
            private final String REQUEST_PARAM_FOO = "foo";

            @Test
            public void shouldProcessFooBarWithResolvedBarValue() throws Exception {
                mockMvc.perform(get("/test")
                        .param(REQUEST_PARAM_BAR, BAR)
                        .param(REQUEST_PARAM_FOO, FOO)
                );

                verify(fooBarService, times(1)).processFooBar(assertArg(
                        fooBar -> assertThat(fooBar.getBar()).isEqualTo(BAR)
                ));
            }

            @Test
            public void shouldProcessFooBarWithResolvedFooValue() throws Exception {
                mockMvc.perform(get("/test")
                        .param(REQUEST_PARAM_BAR, BAR)
                        .param(REQUEST_PARAM_FOO, FOO)
                );

                verify(fooBarService, times(1)).processFooBar(assertArg(
                        fooBar -> assertThat(fooBar.getFoo()).isEqualTo(FOO)
                ));
            }
        }
    }
}
