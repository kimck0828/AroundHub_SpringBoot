package com.kimck0828.aroundhub.aroundhub_springboot.common.exception;

import com.kimck0828.aroundhub.aroundhub_springboot.common.Constants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AroundHubException extends Exception {

  private final Constants.ExceptionClass exceptionClass;
  private final HttpStatus httpStatus;

  public AroundHubException(Constants.ExceptionClass exceptionClass, HttpStatus status,
      String message) {
    super(exceptionClass.toString() + message);
    this.exceptionClass = exceptionClass;
    this.httpStatus = status;
  }

  public int getHttpStatusCode() {
    return httpStatus.value();
  }

  public String getHttpStatusType() {
    return httpStatus.getReasonPhrase();
  }
}
