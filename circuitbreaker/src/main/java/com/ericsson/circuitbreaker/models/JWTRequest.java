package com.ericsson.circuitbreaker.models;

import lombok.Data;

@Data
public class JWTRequest {
  private String userName;
  private String password;
}
