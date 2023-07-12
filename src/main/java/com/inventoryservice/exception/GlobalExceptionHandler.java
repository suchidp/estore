package com.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(com.inventoryservice.exception.CategoryArchivedException.class)
    public ResponseEntity<ExceptionResponse> categoryArchivedException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.inventoryservice.exception.SubCategoryArchivedException.class)
    public ResponseEntity<ExceptionResponse> subCategoryArchivedException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    /*Exception Handling for SubCategoryNotFoundException
    Using SubCategoryNotFoundException ,to check subCategory is find or Not for given subCategoryId
    * @param ex SubCategoryNotFoundException exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(com.inventoryservice.exception.SubCategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> subCategoryNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    /*Exception Handling for InvalidTokenException
    Using InvalidTokenException ,to check token is valid or not
    * @param ex InvalidTokenException exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(com.inventoryservice.exception.InvalidTokenException.class)
    public ResponseEntity<ExceptionResponse> invalidTokenException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);

    }

      /*Exception Handling for CategoryNotFoundException
    Using CategoryNotFoundException ,to check Category is find or Not for given categoryId
    * @param ex CategoryNotFoundException exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(com.inventoryservice.exception.CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> CategoryNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    /*
    Exception Handling of   handleHttpRequestMethodNotSupported
    Using HttpRequestMethodNotSupportedException check Method is supported or not
    * @param ex HttpRequestMethodNotSupportedException exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /*
    Exception Handling for Validations to check HttpMessageNotReadable
     * @param ex HttpMessageNotReadable exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    /*
    Exception Handling using Validations to check MethodArgumentNotValidException
     Using handleMethodArgumentNotValid
     to check MethodArgumentNotValidException
     to validate
	 * @param  MethodArgumentNotValidException ex the exception to handle
	 * @param request the current requestb  nb
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {
        List<String> message = new ArrayList<String>();
        message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> "The field " + error.getField() + " has " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(message);
        return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
    }


    /*
    Exception Handling  to check BindException
	 * @param ex the exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

       /*
        Exception Handling  to check MethodArgumentTypeMismatch .
       It throws a type mismatch when trying  invalid parameter type conversion throws a TypeMismatchException,
       that we can handle with a method
         * @param ex the MethodArgumentTypeMismatchException to handle
         * @param request the current request
         * @return a  ResponseEntity for the response to use
         */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(error);
        return new ResponseEntity<Object>(
                exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
