package net.petrikainulainen.spring.trenches.comment.dto;

/**
 * @author Petri Kainulainen
 */
public class FieldErrorDTO {

    private String field;

    private String message;

    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
