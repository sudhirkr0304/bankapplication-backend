package com.sudhir.bankapplicationbackend.dto;

import org.springframework.http.HttpStatus;

public class SuccessResponse {
    String text;
    HttpStatus httpStatus;

    public SuccessResponse() {
    }

    public SuccessResponse(String text, HttpStatus httpStatus) {
        this.text = text;
        this.httpStatus = httpStatus;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
