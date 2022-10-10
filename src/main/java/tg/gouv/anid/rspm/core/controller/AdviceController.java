package tg.gouv.anid.rspm.core.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Priority;

/**
 * @author Francis AHONSU
 *
 * @since 0.0.1
 */
@ControllerAdvice
@Priority(1)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AdviceController extends ResponseEntityExceptionHandler {

}
