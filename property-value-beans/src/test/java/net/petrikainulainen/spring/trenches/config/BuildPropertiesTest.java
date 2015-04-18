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
public class BuildPropertiesTest {

    public class CreateNew {

        private final String TIME = "17.04.2015 @ 23:53:52 EEST";
        private final String USER_EMAIL = "foo@bar.com";
        private final String USER_NAME = "Foo Bar";

        private BuildProperties properties;

        @Before
        public void createBuildProperties() {
            properties = new BuildProperties(TIME, USER_EMAIL, USER_NAME);
        }

        @Test
        public void shouldSetTime() {
            assertThat(properties.getTime()).isEqualTo(TIME);
        }

        @Test
        public void shouldSetUserEmail() {
            assertThat(properties.getUserEmail()).isEqualTo(USER_EMAIL);
        }

        @Test
        public void shouldSetUserName() {
            assertThat(properties.getUserName()).isEqualTo(USER_NAME);
        }
    }
}
