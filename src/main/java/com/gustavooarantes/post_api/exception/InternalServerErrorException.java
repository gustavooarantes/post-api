package com.gustavooarantes.post_api.exception;

public class InternalServerErrorException extends RuntimeException {
  public InternalServerErrorException() {
    super("An internal error occurred. Please, try again later.");
  }
}
