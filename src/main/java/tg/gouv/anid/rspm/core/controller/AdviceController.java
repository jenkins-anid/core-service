package tg.gouv.anid.rspm.core.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Priority;

@ControllerAdvice
@Priority(1)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AdviceController extends ResponseEntityExceptionHandler {

}
