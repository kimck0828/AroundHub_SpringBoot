package com.kimck0828.aroundhub.aroundhub_springboot.common;

import lombok.Getter;

@Getter
public class Constants {

  public enum ExceptionClass {
    PRODUCT("Product")/*, ORDER("order"), PROVIDER("Provider")*/;

    private final String exceptionClass;

    ExceptionClass(String exceptionClass) {
      this.exceptionClass = exceptionClass;
    }

    @Override
    public String toString() {
      return this.exceptionClass + " Exception. ";
    }
  }
}
