package ru.mfua.botalov.warehouse.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mfua.botalov.warehouse.exception.OrderException;
import ru.mfua.botalov.warehouse.exception.ProductException;
import ru.mfua.botalov.warehouse.exception.UserException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(OrderException.class)
    protected ResponseEntity<String> handleOrderException(OrderException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    protected ResponseEntity<String> handleUserException(UserException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.class)
    protected ResponseEntity<String> handleProductException(ProductException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
