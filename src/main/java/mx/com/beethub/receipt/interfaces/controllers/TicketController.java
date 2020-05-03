package mx.com.beethub.receipt.interfaces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import mx.com.beethub.receipt.facade.ReceiptServiceFacade;
import mx.com.beethub.receipt.interfaces.dto.FileDTO;
import mx.com.beethub.receipt.interfaces.dto.ReceiptDTO;

@Slf4j
@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	private ReceiptServiceFacade receiptService;
		
	@Autowired
	public TicketController(ReceiptServiceFacade receiptService) {
		this.receiptService = receiptService;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/{account}")
	@ApiOperation(value = "Create a Receipt",
			notes= "Create a new Receipt in the account",
			response = ReceiptDTO.class)
	public ResponseEntity<Object> createReceipt(
			@ApiParam(value = "Account number to register a new receipt", required = true) 
			@PathVariable String account, @RequestBody FileDTO ticket){
		
		log.info("Account: {}, key: {}", account, ticket);
		return this.receiptService.createReceiptFromTicket(account, ticket);
	}
	
}
