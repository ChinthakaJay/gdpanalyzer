package com.rootcodelabs.gdpanalyzer.exception.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;
}
