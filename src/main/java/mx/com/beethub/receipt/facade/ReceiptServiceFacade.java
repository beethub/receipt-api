package mx.com.beethub.receipt.facade;

import org.springframework.http.ResponseEntity;

import mx.com.beethub.receipt.interfaces.dto.FileDTO;

public interface ReceiptServiceFacade {
	
	ResponseEntity<Object> getReceipts(String account, String filter);
	ResponseEntity<Object> getReceipt(String account, Long id);
	ResponseEntity<Object> createReceiptFromTicket(String account, FileDTO ticket);
}
