class IllegalArgumentException extends RuntimeException {
  private String message;

  public IllegalArgumentException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}

