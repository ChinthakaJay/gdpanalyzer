package com.rootcodelabs.gdpanalyzer.controller;

import com.rootcodelabs.gdpanalyzer.exception.GdpAnalyzerException;
import com.rootcodelabs.gdpanalyzer.exception.pojo.ErrorCode;
import com.rootcodelabs.gdpanalyzer.exception.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolationException;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(GdpAnalyzerException.class)
    public ResponseEntity<ErrorResponse> handleGdpAnalyzerException(GdpAnalyzerException ex) {
        log.error("GdpAnalyzerException occurred :{}   {}", ex.getErrorCode(), ex.getErrorMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode().getValue(),
                ex.getErrorMessage()),
                ex.getStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("MethodArgumentNotValidException occurred :{}", ex);
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.PARA001.getValue(),
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        log.error("Maximum file upload size exceeded : {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.FILE004.getValue(),
                "Maximum file upload size exceeded"),
                HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralExceptions(Exception ex) {
        log.error("Unforeseen exception occurred, {}", ex);
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.GDPAXXX.getValue(),
                "Unforeseen exception occurred. Please try again"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
