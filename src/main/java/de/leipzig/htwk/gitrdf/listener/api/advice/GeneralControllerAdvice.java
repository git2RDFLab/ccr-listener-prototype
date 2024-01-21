package de.leipzig.htwk.gitrdf.listener.api.advice;

import de.leipzig.htwk.gitrdf.listener.api.model.response.InternalServerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GeneralControllerAdvice {

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<InternalServerErrorResponse> handleIOException(IOException ex) {

        log.info("IOException during request handling.", ex);

        return new ResponseEntity<>(
                InternalServerErrorResponse.unexpectedException(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<InternalServerErrorResponse> handleRuntimeException(RuntimeException ex) {

        log.warn("IOException during request handling.", ex);

        return new ResponseEntity<>(
                InternalServerErrorResponse.unexpectedException(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
