package net.petrikainulainen.spring.trenches.config;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Petri Kainulainen
 */
@RunWith(NestedRunner.class)
public class WebPropertiesTest {

    public class CreateNew {

        private final String SERVER_HOST = "localhost";
        private final int SERVER_PORT = 80;

        public class WhenValidProtocolIsUsed {

            private final String PROTOCOL_HTTP_IN_LOWERCASE = "http";
            private final String PROTOCOL_HTTPS_IN_LOWERCASE = "https";

            private WebProperties properties;

            @Before
            public void createWebProperties() {
                properties = new WebProperties(PROTOCOL_HTTP_IN_LOWERCASE, SERVER_HOST, SERVER_PORT);
            }

            @Test
            public void shouldSetServerHost() {
                assertThat(properties.getServerHost()).isEqualTo(SERVER_HOST);
            }

            @Test
            public void shouldSetServerPort() {
                assertThat(properties.getServerPort()).isEqualTo(SERVER_PORT);
            }

            public class WhenProtocolIsHttp {

                public class WhenProtocolIsInLowercase {

                    @Test
                    public void shouldSetProtocolToHttp() {
                        WebProperties properties = new WebProperties(PROTOCOL_HTTP_IN_LOWERCASE, SERVER_HOST, SERVER_PORT);
                        assertThat(properties.getProtocol()).isEqualTo(PROTOCOL_HTTP_IN_LOWERCASE);
                    }
                }

                public class WhenProtocolNameIsInUppercase {

                    @Test
                    public void shouldSetProtocolToHttp() {
                        WebProperties properties = new WebProperties(PROTOCOL_HTTP_IN_LOWERCASE.toUpperCase(), SERVER_HOST, SERVER_PORT);
                        assertThat(properties.getProtocol()).isEqualTo(PROTOCOL_HTTP_IN_LOWERCASE);
                    }
                }
            }

            public class WhenProtocolIsHttps {


                public class WhenProtocolIsInLowercase {

                    @Test
                    public void shouldSetProtocolToHttps() {
                        WebProperties properties = new WebProperties(PROTOCOL_HTTPS_IN_LOWERCASE, SERVER_HOST, SERVER_PORT);
                        assertThat(properties.getProtocol()).isEqualTo(PROTOCOL_HTTPS_IN_LOWERCASE);
                    }
                }

                public class WhenProtocolNameIsInUppercase {

                    @Test
                    public void shouldSetProtocolToHttps() {
                        WebProperties properties = new WebProperties(PROTOCOL_HTTPS_IN_LOWERCASE.toUpperCase(), SERVER_HOST, SERVER_PORT);
                        assertThat(properties.getProtocol()).isEqualTo(PROTOCOL_HTTPS_IN_LOWERCASE);
                    }
                }
            }
        }

        public class WhenProtocolIsNotValid {

            private final String PROTOCOL_FTP = "ftp";

            @Test(expected = IllegalArgumentException.class)
            public void shouldThrowException() {
                new WebProperties(PROTOCOL_FTP, SERVER_HOST, SERVER_PORT);
            }
        }
    }
}
