package com.example.demo.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum UrlExceptionCode {
    INVALID_URL("유효한 URL 형식이 아닙니다!", HttpStatus.BAD_REQUEST),
    EMPTY_DATA("URL 데이터가 비어선 안됩니다!", HttpStatus.BAD_REQUEST),
    INVALID_DATA("유효한 데이터가 아닙니다!",HttpStatus.BAD_REQUEST),
    NOT_FOUND_URL("해당하는 URL은 존재하지 않습니다!", HttpStatus.NOT_FOUND),
    ALREADY_EXISTS("해당 URL은 이미 저희 서비스에 등록된 URL입니다!", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus status;
}
