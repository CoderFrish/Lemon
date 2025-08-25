package me.coderfrish.plugin.exception;

public class InvalidPluginPackException extends RuntimeException{
    public InvalidPluginPackException(String message) {
        super(message);
    }

    public InvalidPluginPackException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPluginPackException(Throwable cause) {
        super(cause);
    }

    public InvalidPluginPackException() {
        super();
    }
}
