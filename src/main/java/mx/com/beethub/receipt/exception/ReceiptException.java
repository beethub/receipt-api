package mx.com.beethub.receipt.exception;

import org.springframework.http.HttpStatus;

/**
 * ReceiptException
 */
public abstract class ReceiptException extends RuntimeException {
  private static final long serialVersionUID = 6693157922687352637L;

  public ReceiptException(String string) {
		super(string);
	}

  public abstract HttpStatus getStatusCode();
}