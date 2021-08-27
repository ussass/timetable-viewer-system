package ru.trofimov.timetableviewersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class SQLExceptionHandler {

    @ExceptionHandler(value = {SQLException.class})
    public String handleSQLException(SQLException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        SQLNoAccessToDatabaseException sqlNoAccessToDatabaseException = new SQLNoAccessToDatabaseException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return "error";
    }

    @ExceptionHandler(ArithmeticException.class)
    ModelAndView handleException(HttpServletRequest request,  ArithmeticException exception){
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", exception.getMessage());
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("groups/index");
        return mav;
    }
}
