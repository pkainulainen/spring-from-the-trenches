package net.petrikainulainen.spring.trenches.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This bean contains information about the person who packaged this application
 * into a binary.
 *
 * @author Petri Kainulainen
 */
@Component
public class BuildProperties {

    private final String time;

    private final String userEmail;

    private final String userName;

    @Autowired
    public BuildProperties(@Value("${git.build.time}") String time,
                           @Value("${git.build.user.email}") String userEmail,
                           @Value("${git.build.user.name}") String userName) {
        this.time = time;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    /**
     * Returns the build time.
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns the email address of the user who started the build.
     * @return
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Returns the name of the user who started the build.
     * @return
     */
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("time", this.time)
                .append("userEmail", this.userEmail)
                .append("userName", this.userName)
                .toString();
    }
}
