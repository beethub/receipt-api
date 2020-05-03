package mx.com.beethub.receipt.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends ReceiptException {
	 
	private static final long serialVersionUID = 6004707389863818277L;
	
	public ValidationException(String string) {
		super(string);
	}
	
	@Override
	public HttpStatus getStatusCode() {return HttpStatus.BAD_REQUEST;}
	
}
