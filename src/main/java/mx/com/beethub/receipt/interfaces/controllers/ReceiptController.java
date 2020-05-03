package mx.com.beethub.receipt.interfaces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import mx.com.beethub.receipt.facade.ReceiptServiceFacade;

@Slf4j
@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	
	private ReceiptServiceFacade receiptService;
	
	@Autowired
	public ReceiptController(ReceiptServiceFacade receiptService) {
		this.receiptService = receiptService;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{account}")
	@ApiOperation(value = "Return the receipts by page",
	notes= "Return a page of receipts by account number")
	public ResponseEntity<Object> getReceipts(
			@ApiParam(value = "Account number to register a new receipt", required = true) @PathVariable String account,
			@RequestParam(required = false) String filter){
		log.info("List Of receipts, account: {}, filter: {}", account, filter);
		
		return receiptService.getReceipts(account, filter);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{account}/{id}")
	@ApiOperation(value = "Return the receipts by id",
	notes= "Return a receipts by account number and id")
	public ResponseEntity<Object> getReceipt(
			@ApiParam(value = "Account number", required = true) @PathVariable String account,
			@ApiParam(value = "Receipt id", required = true) @PathVariable Long id){
		log.info("Receipt, account: {}, id: {}", account, id);
		
		return receiptService.getReceipt(account, id);
	}
	
}
