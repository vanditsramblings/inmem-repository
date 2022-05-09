package com.rambler.inmem.exception;

/**
 * A custom implementation for exceptions that are thrown for
 *  repository related operation.
 *
 * @author vandit
 */
public class RepositoryException extends Exception{
    private final ErrorCode code;

    public RepositoryException(ErrorCode code) {
        super();
        this.code = code;
    }

    public RepositoryException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    public RepositoryException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public RepositoryException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }
}
