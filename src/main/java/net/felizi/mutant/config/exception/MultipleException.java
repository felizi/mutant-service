package net.felizi.mutant.config.exception;

import java.util.List;

public class MultipleException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final List<ErrorSpec> errors;

  public MultipleException(List<ErrorSpec> errors) {
    super();
    this.errors = errors;
  }

  public List<ErrorSpec> getErrors() {
    return errors;
  }
}