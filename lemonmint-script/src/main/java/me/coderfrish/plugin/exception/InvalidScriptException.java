package me.coderfrish.plugin.exception;

public class InvalidScriptException extends RuntimeException{
    public InvalidScriptException(String message) {
        super(message);
    }

    public InvalidScriptException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidScriptException(Throwable cause) {
        super(cause);
    }

    public InvalidScriptException() {
        super();
    }
}
