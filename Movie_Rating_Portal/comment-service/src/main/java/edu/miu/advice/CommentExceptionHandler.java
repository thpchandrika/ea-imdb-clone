package edu.miu.advice;

import edu.miu.dtos.ExceptionResponse;
import edu.miu.exception.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class CommentExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleInvalidArgument(MethodArgumentNotValidException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), "BAD_REQUEST",
                LocalDateTime.now());
        return response;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CommentNotFoundException.class)
    public ExceptionResponse handleCommentNotFoundException(CommentNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), "INTERNAL_SERVER_ERROR",
                LocalDateTime.now());
        return response;
    }
}
