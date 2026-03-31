package com.exemplo.usuariosapi.dto;
import java.time.LocalDateTime;
import java.util.List;

public class ErroResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
    private String code;
    List<String> errors;

    public ErroResponse(String code, String message, int status, LocalDateTime timestamp, String path, List<String> errors) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
        this.code = code;
        this.errors = errors;
    }

    public String getMessage() {return message;}
    public int getStatus() {return status;}
    public LocalDateTime getTimestamp() {return timestamp;}
    public String getPath() {return path;}
    public String getCode() {return code;}
    public List<String> getErrors() {return errors;}}