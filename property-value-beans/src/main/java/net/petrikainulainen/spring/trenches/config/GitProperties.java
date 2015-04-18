package net.petrikainulainen.spring.trenches.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This class is the "root" class of the class hierarchy that contains information about
 * the state of the Git repository. The property values inserted into this bean and into the
 * other beans of the hierarchy are resolved at build time.
 *
 * @author Petri Kainulainen
 */
@Component
public class GitProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitProperties.class);

    private String branch;

    private final BuildProperties build;

    private final CommitProperties commit;

    private final boolean dirty;

    private final String remoteOriginUrl;

    private final String tags;

    @Autowired
    public GitProperties(@Value("${git.branch}") String branch,
                         BuildProperties build,
                         CommitProperties commit,
                         @Value("${git.dirty}") boolean dirty,
                         @Value("${git.remote.origin.url}") String remoteOriginUrl,
                         @Value("${git.tags}") String tags) {
        this.branch = branch;
        this.build = build;
        this.commit = commit;
        this.dirty = dirty;
        this.remoteOriginUrl = remoteOriginUrl;
        this.tags = tags;
    }

    /**
     * Returns the branch name.
     * @return
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Returns the build information.
     * @return
     */
    public BuildProperties getBuild() {
        return build;
    }

    /**
     * Returns the commit information.
     * @return
     */
    public CommitProperties getCommit() {
        return commit;
    }

    /**
     * Returns true if the repository is dirty (i.e. it contains uncommitted changes)
     * and false otherwise.
     * @return
     */
    public boolean isDirty() {
        return dirty;
    }

    /**
     * Returns the url of the remote origin.
     * @return
     */
    public String getRemoteOriginUrl() {
        return remoteOriginUrl;
    }

    /**
     * Returns the tag names. If no tags is found, this returns an empty string.
     * @return
     */
    public String getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("branch", this.branch)
                .append("build", this.build)
                .append("commit", this.commit)
                .append("dirty", this.dirty)
                .append("remoteOriginUrl", this.remoteOriginUrl)
                .append("tags", this.tags)
                .toString();
    }

    @PostConstruct
    public void writeGitCommitInformationToLog() {
        LOGGER.info("Application was built by using the Git commit: {}", this);
    }
}
