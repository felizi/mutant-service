package net.felizi.mutant.application.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.felizi.mutant.config.exception.MultipleException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { MultipleException.class })
  protected ResponseEntity<Object> handle(MultipleException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getErrors(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
  }
}