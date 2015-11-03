package net.petrikainulainen.spring.trenches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * This component creates new {@link FooBar} objects. The values of the {@code bar} and {@code foo} properties
 * from the following request parameters:
 * <ul>
 *     <li>
 *         The value of the {@code bar} request parameter is set as the value of the {@code bar} property. If the value
 *         of the request parameter is set, the default value is used instead.
 *     </li>
 *     <li>
 *         The value of the {@code foo} request parameter is set as the value of the {@code foo} property. If the value
 *         of the request parameter is set, the default value is used instead.
 *     </li>
 * </ul>
 * @author Petri Kainulainen
 */
public final class FooBarHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooBarHandlerMethodArgumentResolver.class);

    private static final String DEFAULT_BAR = "defaultBar";
    private static final String DEFAULT_FOO = "defaultFoo";

    private static final String REQUEST_PARAM_NAME_BAR = "bar";
    private static final String REQUEST_PARAM_NAME_FOO = "foo";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(FooBar.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        LOGGER.debug("Creating a FooBar object from the incoming request.");

        String bar = nativeWebRequest.getParameter(REQUEST_PARAM_NAME_BAR);
        String foo = nativeWebRequest.getParameter(REQUEST_PARAM_NAME_FOO);

        if (isNotSet(bar)) {
            bar = DEFAULT_BAR;
        }

        if (isNotSet(foo)) {
            foo = DEFAULT_FOO;
        }

        LOGGER.debug("Returning a new FooBar object with bar: {} and foo: {}", bar, foo);

        return new FooBar(bar, foo);
    }

    private boolean isNotSet(String value) {
        return value == null;
    }
}
