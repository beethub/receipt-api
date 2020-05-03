package mx.com.beethub.receipt.facade.impl;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.com.beethub.receipt.application.ReceiptService;
import mx.com.beethub.receipt.facade.AssemberFacade;
import mx.com.beethub.receipt.facade.ReceiptServiceFacade;
import mx.com.beethub.receipt.interfaces.ManageResponse;
import mx.com.beethub.receipt.interfaces.dto.FileDTO;
import mx.com.beethub.receipt.interfaces.dto.ReceiptDTO;
import mx.com.beethub.receipt.interfaces.dto.ResponseDTO;

@Service
public class ReceiptServiceFacadeImpl implements ReceiptServiceFacade {
	
	private ReceiptService receiptService;
	
	@Autowired
	public ReceiptServiceFacadeImpl(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@Override
	public ResponseEntity<Object> getReceipts(String account, String filter) {
		
		Supplier<ResponseDTO<ReceiptDTO>> receiptsDto = () -> {
			List<ReceiptDTO> receipts = this.receiptService.find(account, filter)
					.stream().map(r -> AssemberFacade.toReceiptDto.apply(r))
					.collect(Collectors.toList());
			ResponseDTO<ReceiptDTO> ret = new ResponseDTO<>();
			ret.setData(receipts);
			return ret;
		};
		
		return ManageResponse.processRequest(receiptsDto);
	}

	@Override
	public ResponseEntity<Object> getReceipt(String account, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> createReceiptFromTicket(String account, FileDTO ticket) {
		Supplier<ReceiptDTO> saveReceipt = () -> {
			return AssemberFacade.toReceiptDto.apply(
					this.receiptService.createFromTicket(account, AssemberFacade.toFile.apply(ticket))
				);
		};
		return ManageResponse.processRequest(saveReceipt);
	}

}
