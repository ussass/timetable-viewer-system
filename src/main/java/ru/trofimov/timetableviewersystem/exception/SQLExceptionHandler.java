package ru.trofimov.timetableviewersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class SQLExceptionHandler {

    @ExceptionHandler(value = {SQLException.class})
    public ResponseEntity<Object> handleSQLException(SQLException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        SQLNoAccessToDatabaseException sqlNoAccessToDatabaseException = new SQLNoAccessToDatabaseException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(sqlNoAccessToDatabaseException, badRequest);
    }
}
