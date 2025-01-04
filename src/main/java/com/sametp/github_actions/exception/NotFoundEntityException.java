package com.sametp.github_actions.exception;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException() {
        super("Entity Not Found");
    }
}
