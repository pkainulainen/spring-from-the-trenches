package net.petrikainulainen.spring.trenches.comment.dto;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Petri Kainulainen
 */
public class ValidationErrorDTOTest {

    private static final String MESSAGE = "Foo is not valid";
    private static final String FIELD = "foo";

    @Test
    public void addValidationError_ShouldAddNewValidationError() {
        ValidationErrorDTO validationError = new ValidationErrorDTO();
        validationError.addFieldError(FIELD, MESSAGE);

        List<FieldErrorDTO> fieldErrors = validationError.getFieldErrors();
        assertThat(fieldErrors.size(), is(1));

        FieldErrorDTO fieldError = fieldErrors.get(0);
        assertThat(fieldError.getField(), is(FIELD));
        assertThat(fieldError.getMessage(), is(MESSAGE));
    }
}
