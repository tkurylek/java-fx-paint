package com.futureprocessing.fxpaint.exceptions;

public class FileSaveNotPossibleException extends RuntimeException {

    public FileSaveNotPossibleException(String message, Exception cause) {
        super(message, cause);
    }

    public FileSaveNotPossibleException(String message, Object... args) {
        super(String.format(message, args));
    }
}
