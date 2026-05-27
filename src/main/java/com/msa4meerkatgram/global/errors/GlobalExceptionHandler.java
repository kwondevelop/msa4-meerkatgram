package com.msa4meerkatgram.global.errors;

import com.msa4meerkatgram.global.errors.custom.NotRegisteredException;
import com.msa4meerkatgram.global.responses.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

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