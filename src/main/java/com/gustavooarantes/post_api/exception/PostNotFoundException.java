package com.gustavooarantes.post_api.exception;

public class PostNotFoundException extends RuntimeException {
  public PostNotFoundException(Long id) {
    super("Post com id " + id + " não encontrado.");
  }
}
