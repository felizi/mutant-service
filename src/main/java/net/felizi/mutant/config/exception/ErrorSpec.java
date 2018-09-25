package net.felizi.mutant.config.exception;

public class ErrorSpec {
  private final String message;

  public ErrorSpec(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}