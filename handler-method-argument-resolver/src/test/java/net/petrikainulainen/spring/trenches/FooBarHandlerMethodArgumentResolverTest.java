package net.petrikainulainen.spring.trenches;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Petri Kainulainen
 */
@RunWith(NestedRunner.class)
public class FooBarHandlerMethodArgumentResolverTest {

    public class ResolveArgument {

        private FooBarHandlerMethodArgumentResolver argumentResolver = new FooBarHandlerMethodArgumentResolver();
        private MethodParameter methodParameter;
        private ModelAndViewContainer modelAndViewContainer;
        private NativeWebRequest nativeWebRequest;
        private WebDataBinderFactory webDataBinderFactory;

        @Before
        public void createUnusedMocks() {
            methodParameter = mock(MethodParameter.class);
            modelAndViewContainer = mock(ModelAndViewContainer.class);
            webDataBinderFactory = mock(WebDataBinderFactory.class);
        }

        public class WhenRequestParametersAreNotSet {

            @Before
            public void createEmptyRequest() {
                nativeWebRequest = new ServletWebRequest(new MockHttpServletRequest());
            }

            @Test
            public void shouldCreateFooBarObjectWithDefaultBarValue() throws Exception {
                FooBar created = (FooBar) argumentResolver.resolveArgument(methodParameter,
                        modelAndViewContainer,
                        nativeWebRequest,
                        webDataBinderFactory);

                assertThat(created.getBar()).isEqualTo("defaultBar");
            }

            @Test
            public void shouldCreateFooBarObjectWithDefaultFooValue() throws Exception {
                FooBar created = (FooBar) argumentResolver.resolveArgument(methodParameter,
                        modelAndViewContainer,
                        nativeWebRequest,
                        webDataBinderFactory);

                assertThat(created.getFoo()).isEqualTo("defaultFoo");
            }
        }

        public class WhenRequestParametersAreSet {

            private final String BAR = "bar";
            private final String FOO = "foo";

            private final String REQUEST_PARAM_BAR = "bar";
            private final String REQUEST_PARAM_FOO = "foo";

            @Before
            public void createRequest() {
                MockHttpServletRequest request = new MockHttpServletRequest();
                request.setParameter(REQUEST_PARAM_BAR, BAR);
                request.setParameter(REQUEST_PARAM_FOO, FOO);

                nativeWebRequest = new ServletWebRequest(request);
            }

            @Test
            public void shouldCreateFooBarObjectWithResolvedBarValue() throws Exception {
                FooBar created = (FooBar) argumentResolver.resolveArgument(methodParameter,
                        modelAndViewContainer,
                        nativeWebRequest,
                        webDataBinderFactory);

                assertThat(created.getBar()).isEqualTo(BAR);
            }

            @Test
            public void shouldCreateFooBarObjectWithResolvedFooValue() throws Exception {
                FooBar created = (FooBar) argumentResolver.resolveArgument(methodParameter,
                        modelAndViewContainer,
                        nativeWebRequest,
                        webDataBinderFactory);

                assertThat(created.getFoo()).isEqualTo(FOO);
            }
        }
    }
}
