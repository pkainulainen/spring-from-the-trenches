package net.petrikainulainen.spring.trenches.comment.controller;

import net.petrikainulainen.spring.trenches.comment.dto.CommentDTO;
import net.petrikainulainen.spring.trenches.comment.dto.ValidationErrorDTO;
import net.petrikainulainen.spring.trenches.common.util.LocaleContextHolderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author Petri Kainulainen
 */
@Controller
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private LocaleContextHolderWrapper localeWrapper;

    private MessageSource messageSource;

    @Autowired
    public CommentController(LocaleContextHolderWrapper localeWrapper, MessageSource messageSource) {
        this.localeWrapper = localeWrapper;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    @ResponseBody
    public CommentDTO add(@Valid @RequestBody CommentDTO comment) {
        LOGGER.debug("Received comment: {}", comment);
        return comment;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        LOGGER.debug("Handling form validation error");

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        Locale currentLocale = localeWrapper.getCurrentLocale();

        ValidationErrorDTO dto = new ValidationErrorDTO();

        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError, currentLocale);
            LOGGER.debug("Adding error message: {} to field: {}", localizedErrorMessage, fieldError.getField());
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return dto;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError, Locale locale) {
        String localizedErrorMessage = null;

        String[] fieldErrorCodes = fieldError.getCodes();
        for (int index = 0; index < fieldErrorCodes.length; index++) {
            String fieldErrorCode = fieldErrorCodes[index];
            LOGGER.debug("Finding localized message with code: {} and locale: {}", fieldErrorCode, locale);

            localizedErrorMessage = getMessageForCode(fieldErrorCode, fieldError.getArguments(), locale);
            LOGGER.debug("Found message: {}", localizedErrorMessage);

            if (localizedErrorMessage != null) {
                LOGGER.debug("Message: {} is valid. Returning message.", localizedErrorMessage);
                break;
            }
        }

        //If a message was not found, return the most accurate field error code instead.
        if (localizedErrorMessage == null) {
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }

    private String getMessageForCode(String code, Object[] params, Locale locale) {
        String message = messageSource.getMessage(code, params, locale);

        if (message != null && message.equals(code)) {
            message = null;
        }

        return message;
    }
}
