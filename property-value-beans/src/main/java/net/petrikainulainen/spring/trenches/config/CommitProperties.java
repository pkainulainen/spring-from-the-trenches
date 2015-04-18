package net.petrikainulainen.spring.trenches.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class contains the information of the latest commit that is included
 * in the build.
 *
 * @author Petri Kainulainen
 */
@Component
public class CommitProperties {

    private final String describe;

    private final String describeShort;

    private final String fullMessage;

    private final String id;

    private final String idAbbrev;

    private final String shortMessage;

    private final String time;

    private final String userEmail;

    private final String userName;

    @Autowired
    public CommitProperties(@Value("${git.commit.id.describe}") String describe,
                            @Value("${git.commit.id.describe-short}") String describeShort,
                            @Value("${git.commit.message.full}") String fullMessage,
                            @Value("${git.commit.id}") String id,
                            @Value("${git.commit.id.abbrev}") String idAbbrev,
                            @Value("${git.commit.message.short}") String shortMessage,
                            @Value("${git.commit.time}") String time,
                            @Value("${git.commit.user.email}") String userEmail,
                            @Value("${git.commit.user.name}") String userName) {
        this.describe = describe;
        this.describeShort = describeShort;
        this.fullMessage = fullMessage;
        this.id = id;
        this.idAbbrev = idAbbrev;
        this.shortMessage = shortMessage;
        this.time = time;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getDescribe() {
        return describe;
    }

    public String getDescribeShort() {
        return describeShort;
    }

    /**
     * Returns the full commit message.
     * @return
     */
    public String getFullMessage() {
        return fullMessage;
    }

    /**
     * Returns the full commit id.
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the commit id abbrev.
     * @return
     */
    public String getIdAbbrev() {
        return idAbbrev;
    }

    /**
     * Returns the short commit message.
     * @return
     */
    public String getShortMessage() {
        return shortMessage;
    }

    /**
     * Returns the time when the commit was made.
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns the email address of the user who made the commit.
     * @return
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Returns the name of the user who made the commit.
     * @return
     */
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("describe", this.describe)
                .append("describeShort", this.describeShort)
                .append("fullMessage", this.fullMessage)
                .append("id", this.id)
                .append("idAbbrev", this.idAbbrev)
                .append("shortMessage", this.shortMessage)
                .append("time", this.time)
                .append("userEmail", this.userEmail)
                .append("userName", this.userName)
                .toString();
    }
}
