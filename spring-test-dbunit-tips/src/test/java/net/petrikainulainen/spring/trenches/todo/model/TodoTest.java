package net.petrikainulainen.spring.trenches.todo.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Petri Kainulainen
 */
public class TodoTest {

    private static final int MAX_LENGTH_DESCRIPTION = 500;
    private static final int MAX_LENGTH_TITLE = 100;

    private String DESCRIPTION = "description";
    private String TITLE = "title";

    @Test(expected = NullPointerException.class)
    public void build_TitleIsNull_ShouldThrowException() {
        Todo.getBuilder()
                .title(null)
                .description(DESCRIPTION)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_TitleIsEmpty_ShouldThrowException() {
        Todo.getBuilder()
                .title("")
                .description(DESCRIPTION)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_TitleIsTooLong_ShouldThrowException() {
        String tooLongTitle = createStringWithLength(MAX_LENGTH_TITLE + 1);

        Todo.getBuilder()
                .title(tooLongTitle)
                .description(DESCRIPTION)
                .build();
    }

    @Test
    public void build_MaxLengthTitle_ShouldCreateNewObject() {
        String maxLengthTitle = createStringWithLength(MAX_LENGTH_TITLE);

        Todo todo = Todo.getBuilder()
                .title(maxLengthTitle)
                .description(DESCRIPTION)
                .build();

        assertThat(todo.getTitle()).isEqualTo(maxLengthTitle);
        assertThat(todo.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    public void build_TitleAndDescriptionGiven_ShouldCreateNewObject() {
        Todo todo = Todo.getBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .build();

        assertThat(todo.getTitle()).isEqualTo(TITLE);
        assertThat(todo.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    public void build_DescriptionIsNull_ShouldCreateNewObject() {
        Todo todo = Todo.getBuilder()
                .title(TITLE)
                .description(null)
                .build();

        assertThat(todo.getTitle()).isEqualTo(TITLE);
        assertThat(todo.getDescription()).isNull();
    }

    @Test
    public void build_DescriptionIsEmpty_ShouldCreateNewObject() {
        Todo todo = Todo.getBuilder()
                .title(TITLE)
                .description("")
                .build();

        assertThat(todo.getTitle()).isEqualTo(TITLE);
        assertThat(todo.getDescription()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_DescriptionIsTooLong_ShouldThrowException() {
        String tooLongDescription = createStringWithLength(MAX_LENGTH_DESCRIPTION + 1);

        Todo.getBuilder()
                .title(TITLE)
                .description(tooLongDescription)
                .build();
    }

    @Test
    public void build_MaxLengthDescription_ShouldCreateNewObject() {
        String maxLengthDescription = createStringWithLength(MAX_LENGTH_DESCRIPTION);

        Todo todo = Todo.getBuilder()
                .title(TITLE)
                .description(maxLengthDescription)
                .build();

        assertThat(todo.getTitle()).isEqualTo(TITLE);
        assertThat(todo.getDescription()).isEqualTo(maxLengthDescription);
    }

    private String createStringWithLength(int lenght) {
        StringBuilder string = new StringBuilder();

        for (int index = 0; index < lenght; index++) {
            string.append("a");
        }

        return string.toString();
    }
}
