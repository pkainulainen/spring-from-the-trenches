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
public class ApplicationPropertiesTest {

    public class CreateNew {

        private final String NAME = "application";
        private final String PROTOCOL = "http";
        private final String SERVER_HOST = "localhost";
        private final int SERVER_PORT = 80;

        private ApplicationProperties properties;
        private WebProperties webProperties;


        @Before
        public void createApplicationProperties() {
            webProperties = new WebProperties(PROTOCOL, SERVER_HOST, SERVER_PORT);
            properties = new ApplicationProperties(NAME, true, webProperties);
        }

        @Test
        public void shouldSetName() {
            assertThat(properties.getName()).isEqualTo(NAME);
        }

        @Test
        public void shouldSetProductionModeEnabled() {
            assertThat(properties.isProductionModeEnabled()).isTrue();
        }

        @Test
        public void shouldSetWebProperties() {
            assertThat(properties.getWebProperties()).isEqualTo(webProperties);
        }
    }
}
