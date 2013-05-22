package net.petrikainulainen.spring.trenches.comment.controller;

import net.petrikainulainen.spring.trenches.comment.dto.ValidationErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

/**
 * @author Petri Kainulainen
 */
@ControllerAdvice
public class RestErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

    private MessageSource messageSource;

    @Autowired
    public RestErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
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
        Locale currentLocale = LocaleContextHolder.getLocale();

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
