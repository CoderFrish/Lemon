package me.coderfrish.exception;

@Deprecated
public class LemonMintRuntimeException extends RuntimeException {
    public LemonMintRuntimeException(String message) {
        super(message);
    }

    public LemonMintRuntimeException(String message, Throwable cause) {
      super(message, cause);
    }

    public LemonMintRuntimeException(Throwable cause) {
      super(cause);
    }

    public LemonMintRuntimeException() {
      super();
    }
}
