package com.example.restjpajunit.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ErrorResponse {

    private int errorCode;
    private String message;
}
