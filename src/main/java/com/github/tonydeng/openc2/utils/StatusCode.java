package com.github.tonydeng.openc2.utils;

public enum StatusCode {
  /**
   * Interim response to inform the client that the request was accepted but not complete yet
   */
  PROCESSING(102),
  /**
   * Request was successful
   */
  OK(200),
  /**
   * Target resource has been assigned a new permanent URI
   */
  MOVED(301),
  /**
   * Server cannot proocess the request due to something taht is preceived to be a client error
   */
  BAD_REQUEST(400),
  /**
   * Request lacks valid authentication credentials for the target resource or authorization has
   * been refused
   */
  UNAUTHORIZED(401),
  /**
   * Server understood the request but refuses to authorize it
   */
  PORBIDOEN(403),
  /**
   * Server encountered an unexpected condition that prevented it from fulfilling the request
   */
  SERVER_ERROR(500),
  /**
   * Server does not support the functionality required to fulfill the request
   */
  NOT_IMPLEMENTED(501);

  private int type;

  StatusCode(int type) {
    this.type = type;
  }

  public int getValue() {
    return type;
  }

  @Override
  public String toString() {
    return String.valueOf(type);
  }
}

