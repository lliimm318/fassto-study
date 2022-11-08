package com.example.study.exception;

import com.example.study.exception.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> baseExceptionHandle(final BaseException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode.getStatus(), errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> baseExceptionHandle(final Exception e) {
        return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()),
                HttpStatus.valueOf(400));
    }

    //validation @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> exceptionHandel(final MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorResponse(404, e.getMessage()),
                HttpStatus.valueOf(404));
    }

    //@RequestParam
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResponse> exceptionHandel(final MissingServletRequestParameterException e) {
        return new ResponseEntity<>(new ErrorResponse(404, e.getMessage()),
                HttpStatus.valueOf(404));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> exceptionHandle(final Exception e) {
        //e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(500, e.getMessage()),
                HttpStatus.valueOf(500));
    }

}
