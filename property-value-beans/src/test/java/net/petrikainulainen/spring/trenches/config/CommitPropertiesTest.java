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
public class CommitPropertiesTest {

    public class CreateNew {

        private final String DESCRIBE = "1bdfe9c-dirty";
        private final String DESCRIBE_SHORT = "1bdfe9c-dirty";
        private final String ID = "1bdfe9cf22b550a3ebe170f60df165e5c26448f9";
        private final String ID_ABBREV = "1bdfe9c";
        private final String MESSAGE_FULL = "Declare PropertySourcesPlaceholderConfigurer in a static @Bean method";
        private final String MESSAGE_SHORT = "Declare PropertySourcesPlaceholderConfigurer in a static @Bean method";
        private final String TIME = "16.04.2015 @ 23:35:23 EEST";
        private final String USER_EMAIL = "foo@bar.com";
        private final String USER_NAME = "Foo Bar";

        private CommitProperties properties;

        @Before
        public void createCommitProperties() {
            properties = new CommitProperties(DESCRIBE,
                    DESCRIBE_SHORT,
                    MESSAGE_FULL,
                    ID,
                    ID_ABBREV,
                    MESSAGE_SHORT,
                    TIME,
                    USER_EMAIL,
                    USER_NAME);
        }

        @Test
        public void shouldSetDescribe() {
            assertThat(properties.getDescribe()).isEqualTo(DESCRIBE);
        }

        @Test
        public void shouldSetDescribeShort() {
            assertThat(properties.getDescribeShort()).isEqualTo(DESCRIBE_SHORT);
        }

        @Test
        public void shouldSetId() {
            assertThat(properties.getId()).isEqualTo(ID);
        }

        @Test
        public void shouldSetIdAbbrev() {
            assertThat(properties.getIdAbbrev()).isEqualTo(ID_ABBREV);
        }

        @Test
        public void shouldSetFullMessage() {
            assertThat(properties.getFullMessage()).isEqualTo(MESSAGE_FULL);
        }

        @Test
        public void shouldSetShortMessage() {
            assertThat(properties.getShortMessage()).isEqualTo(MESSAGE_SHORT);
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
