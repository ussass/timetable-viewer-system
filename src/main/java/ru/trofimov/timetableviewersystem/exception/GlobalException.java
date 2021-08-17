package ru.trofimov.timetableviewersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleSQLException(SQLException e){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NO_CONTENT.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.OK);
    }
}
