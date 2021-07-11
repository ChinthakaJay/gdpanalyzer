package com.rootcodelabs.gdpanalyzer.exception;

import com.rootcodelabs.gdpanalyzer.exception.pojo.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */

@Getter
@AllArgsConstructor
public class GdpAnalyzerException extends Exception {
    private final HttpStatus statusCode;
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final String logMessage;
}
