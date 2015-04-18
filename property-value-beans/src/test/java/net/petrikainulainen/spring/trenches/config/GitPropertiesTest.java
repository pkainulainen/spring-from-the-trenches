package net.petrikainulainen.spring.trenches.config;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Petri Kainulainen
 */
@RunWith(NestedRunner.class)
public class GitPropertiesTest {

    public class CreateNew {

        private final String BRANCH = "master";
        private final BuildProperties BUILD_PROPERTIES = mock(BuildProperties.class);
        private final CommitProperties COMMIT_PROPERTIES = mock(CommitProperties.class);
        private final boolean DIRTY = true;
        private final String REMOTE_ORIGIN_URL = "git@github.com:pkainulainen/spring-from-the-trenches.git";
        private final String TAGS = "tag";

        private GitProperties properties;

        @Before
        public void createGitProperties() {
            properties = new GitProperties(BRANCH,
                    BUILD_PROPERTIES,
                    COMMIT_PROPERTIES,
                    DIRTY,
                    REMOTE_ORIGIN_URL,
                    TAGS);
        }

        @Test
        public void shouldSetBranch() {
            assertThat(properties.getBranch()).isEqualTo(BRANCH);
        }

        @Test
        public void shouldSetBuildProperties() {
            assertThat(properties.getBuild()).isEqualTo(BUILD_PROPERTIES);
        }

        @Test
        public void shouldSetCommitProperties() {
            assertThat(properties.getCommit()).isEqualTo(COMMIT_PROPERTIES);
        }

        @Test
        public void shouldSetDirtyToTrue() {
            assertThat(properties.isDirty()).isTrue();
        }

        @Test
        public void shouldSetRemoteOriginUrl() {
            assertThat(properties.getRemoteOriginUrl()).isEqualTo(REMOTE_ORIGIN_URL);
        }

        @Test
        public void shouldSetTags() {
            assertThat(properties.getTags()).isEqualTo(TAGS);
        }
    }
}
