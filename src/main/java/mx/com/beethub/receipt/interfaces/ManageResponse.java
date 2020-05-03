package mx.com.beethub.receipt.interfaces;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import mx.com.beethub.receipt.exception.ReceiptException;
import mx.com.beethub.receipt.interfaces.dto.ErrorDTO;

@Slf4j
public class ManageResponse{
	
	static final String DEFAULT_ERROR_MESSAGE = "An unknown error occurs please";
	
	public static <T> ResponseEntity<Object> processRequest(Supplier<T> supplier){
		try {
			return new ResponseEntity<>(supplier.get(), HttpStatus.OK);
		}catch(ReceiptException e) {
			ManageResponse.log.error(e.getClass().getName(), e);
		    return new ResponseEntity<>(new ErrorDTO(e.getMessage()), e.getStatusCode() );
		}catch(Exception e) {
		    log.error("EXCEPTION", e);
		    return new ResponseEntity<>(new ErrorDTO(DEFAULT_ERROR_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
