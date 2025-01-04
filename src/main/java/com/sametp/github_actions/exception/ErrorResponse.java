package com.sametp.github_actions.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorResponse {

    private String message;
    private Map<String,String> errors;
    public ErrorResponse(){
        this.message = "oops! something went wrong.";
        this.errors = Map.of();
    }

    public ErrorResponse(String message){
        this.message = message;
        this.errors = Map.of();
    }
    public ErrorResponse(String message,Map<String,String> errors){
        this.message = message;
        this.errors = errors;
    }
}
