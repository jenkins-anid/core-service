package tg.gouv.anid.rspm.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tg.gouv.anid.common.entities.exception.ApplicationException;
import tg.gouv.anid.common.entities.exception.ResourceNotFoundException;
import tg.gouv.anid.common.entities.util.Response;
import tg.gouv.anid.rspm.core.config.CustomMessageSource;
import tg.gouv.anid.rspm.core.util.ResponseUtil;

import javax.annotation.Priority;

/**
 * @author Francis AHONSU
 *
 * @since 0.0.1
 */
@ControllerAdvice
@Priority(1)
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j(topic = "EventLog")
public class AdviceController extends ResponseEntityExceptionHandler {

    private final CustomMessageSource messageSource;
    public AdviceController(CustomMessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @ExceptionHandler(value
            =  ResourceNotFoundException.class )
    protected ResponseEntity<Response> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        String bodyOfResponse = this.messageSource.getMessage(ex.getMessage());
        logger(ex);
        return new ResponseEntity<>(ResponseUtil.errorResponse(HttpStatus.NOT_FOUND, bodyOfResponse), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value
            =  ApplicationException.class )
    protected ResponseEntity<Response> handleApplicationException(
            ApplicationException ex, WebRequest request) {
        String bodyOfResponse = this.messageSource.getMessage(ex.getMessage());
        logger(ex);
        return new ResponseEntity<>(ResponseUtil
                .errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, bodyOfResponse),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logger(Exception ex) {
        log.error("===> Error Trace: {}", ex.getCause().toString());
        log.error("===> Error Message: {}", this.messageSource.getMessage(ex.getMessage()));
    }

}
