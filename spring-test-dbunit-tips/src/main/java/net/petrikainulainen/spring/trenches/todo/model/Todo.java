package net.petrikainulainen.spring.trenches.todo.model;

import javax.persistence.*;

import static net.petrikainulainen.spring.trenches.util.PreCondition.isTrue;
import static net.petrikainulainen.spring.trenches.util.PreCondition.notEmpty;
import static net.petrikainulainen.spring.trenches.util.PreCondition.notNull;

/**
 * This entity contains the information of a single todo entry.
 * @author Petri Kainulainen
 */
@Entity
@Table(name="todos")
public class Todo {

    private static final int MAX_LENGTH_DESCRIPTION = 500;
    private static final int MAX_LENGTH_TITLE = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", nullable = true, length = MAX_LENGTH_DESCRIPTION)
    private String description;

    @Column(name = "title", nullable = false, length = MAX_LENGTH_TITLE)
    private String title;

    @Version
    private long version;

    public Todo() {

    }

    private Todo(Builder builder) {
        this.description = builder.description;
        this.title = builder.title;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public long getVersion() {
        return version;
    }

    /**
     * This entity is so simple that you don't really need to use the builder pattern (use a constructor instead).
     * I use the builder pattern here because it makes the code a bit more easier to read.
     */
    public static class Builder {

        private String description;
        private String title;

        private Builder() {}

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Todo build() {
            Todo build = new Todo(this);

            notNull(build.getTitle(), "Title cannot be null.");
            notEmpty(build.getTitle(), "Title cannot be empty.");
            isTrue(build.getTitle().length() <= MAX_LENGTH_TITLE,
                    "The maximum length of the title is <%d> characters.",
                    MAX_LENGTH_TITLE
            );

            String description = build.getDescription();
            isTrue((description == null) || (description.length() <= MAX_LENGTH_DESCRIPTION),
                    "The maximum length of the description is <%d> characters.",
                    MAX_LENGTH_DESCRIPTION
            );

            return build;
        }
    }
}
