package com.example.study.exception;

import com.example.study.exception.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> baseExceptionHandle(final BaseException e, HttpServletRequest request) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                errorCode.getStatus(),
                errorCode.getMessage(),
                request.getServletPath()),
                HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> exceptionHandler(final Exception e, HttpServletRequest request) {

        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                400,
                e.getMessage(),
                request.getServletPath()),
                HttpStatus.valueOf(400));
    }

    //validation @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> exceptionHandler(final MethodArgumentNotValidException e, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                404,
                e.getMessage(),
                request.getServletPath()),
                HttpStatus.valueOf(404));
    }

    //@RequestParam
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResponse> exceptionHandler(final MissingServletRequestParameterException e, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                404,
                e.getMessage(),
                request.getServletPath()),
                HttpStatus.valueOf(404));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> exceptionHandle(final Exception e, HttpServletRequest request) {
        //e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                500,
                e.getMessage(),
                request.getServletPath()),
                HttpStatus.valueOf(500));
    }

}
