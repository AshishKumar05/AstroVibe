package com.api.entities;

public class ApiResponse<T> {

    private String status;
    private int statusCode;
    private String message;
    private T data;

    public ApiResponse(String status, int statusCode, String message, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // Getters and setters
}
