package az.turing.cinemamasterapp.exception;

import az.turing.cinemamasterapp.model.dto.response.GlobalResponse;
import az.turing.cinemamasterapp.model.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<GlobalResponse> alreadyExistsExceptionHandler(AlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalResponse.builder()
                .id(UUID.randomUUID())
                .error_code(ErrorCode.ALREADY_EXISTS)
                .error_message(ex.getLocalizedMessage())
                .time(LocalDateTime.now())
                .build());

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalResponse> notFoundExceptionHandler(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalResponse.builder()
                .id(UUID.randomUUID())
                .error_code(ErrorCode.NOT_FOUND)
                .error_message(ex.getLocalizedMessage())
                .time(LocalDateTime.now())
                .build());

    }
}
