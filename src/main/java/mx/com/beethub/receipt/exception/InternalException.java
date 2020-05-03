package mx.com.beethub.receipt.exception;

import org.springframework.http.HttpStatus;

/**
 * InternalException
 */
public class InternalException extends ReceiptException {

  private static final long serialVersionUID = -8399919033375964451L;
  private static final String ERROR_MESSAGE = "An error occurs, please try again later";

  public InternalException() {
    super(ERROR_MESSAGE);
  }

  @Override
  public HttpStatus getStatusCode() {
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}