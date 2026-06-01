package com.msa4meerkatgram.global.errors;

import com.msa4meerkatgram.global.errors.custom.InvalidTokenException;
import com.msa4meerkatgram.global.errors.custom.NotRegisteredException;
import com.msa4meerkatgram.global.responses.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final RestClient.Builder builder;

    public GlobalExceptionHandler(RestClient.Builder builder) {
        this.builder = builder;
    }

    @ExceptionHandler(NotRegisteredException.class)
    public ResponseEntity<GlobalResponse<String>> notRegisteredHandle(NotRegisteredException e) {

        return ResponseEntity.status(400).body(
            GlobalResponse.<String>builder()
                .code("E01")
                .message("로그인 에러")
                .data(e.getMessage())
                .build()
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GlobalResponse<String>> authenticationHandle(AuthenticationException e) {
        return ResponseEntity.status(401).body(
            GlobalResponse.<String>builder()
                .code("E02")
                .message("UNAUTHENTICATED_ERROR")
                .data("로그인이 필요한 서비스입니다")
                .build()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GlobalResponse<String>> authenticationHandle(AccessDeniedException e) {
        return ResponseEntity.status(403).body(
            GlobalResponse.<String>builder()
                .code("E03")
                .message("UNAUTHORIZED_ERROR")
                .data("권한이 부족합니다")
                .build()
            );
    }
    
    
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<GlobalResponse<String>> invalidTokenHandle(InvalidTokenException e) {

        return ResponseEntity.status(400).body(
            GlobalResponse.<String>builder()
                .code("E04")
                .message("토큰 이상")
                .data(e.getMessage())
                .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse<List<String>>> methodArgumentNotValidException(
        MethodArgumentNotValidException e
    ) {

        List<String> errors = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + " : " + error.getDefaultMessage())
            .collect(Collectors.toList());

        return ResponseEntity.status(400).body(
            GlobalResponse.<List<String>>builder()
                .code("E21")
                .message("요청 파라미터에 이상이 있습니다")
                .data(errors)
                .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse<String>> othersHandle(Exception e) {

        log.error(
            String.format(
                "시스템 에러 : %s\n%s",
                e.getMessage(),
                Arrays.toString(e.getStackTrace())
            )
        );

        return ResponseEntity.status(500).body(
            GlobalResponse.<String>builder()
                .code("E99")
                .message("시스템 에러")
                .data("현재 서비스 이용이 불가합니다. 잠시 후 다시 시도해 주십시오.")
                .build()
        );
    }
}