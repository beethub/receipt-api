package mx.com.beethub.receipt.facade;

import java.util.function.Function;

import mx.com.beethub.receipt.interfaces.dto.FileDTO;
import mx.com.beethub.receipt.interfaces.dto.InvoiceDTO;
import mx.com.beethub.receipt.interfaces.dto.ReceiptDTO;
import mx.com.beethub.receipt.model.entity.Invoice;
import mx.com.beethub.receipt.model.entity.Receipt;
import mx.com.beethub.receipt.model.value.File;

public class AssemberFacade {
	public static final Function<FileDTO, File> toFile = fileDto -> {
		File ret = null;
		if(fileDto != null) {
			ret = new File();
			ret.setKey(fileDto.getKey());
		}
		return ret;
	};
	
	public static final Function<File, FileDTO> toFileDto = file -> {
		FileDTO ret = null;
		if(file != null) {
			ret = new FileDTO();
			ret.setKey(file.getKey());
		}
		return ret;
	};
	
	public static final Function<Invoice, InvoiceDTO> toInvoiceDto = invoice -> {
		InvoiceDTO ret = null;
		if(invoice != null) {
			ret = new InvoiceDTO();
			ret.setPdf(AssemberFacade.toFileDto.apply(invoice.getPdf()));
			ret.setXml(AssemberFacade.toFileDto.apply(invoice.getXml()));
		}
		return ret;
	};
	
	public static final Function<Receipt, ReceiptDTO> toReceiptDto = receipt -> {
		ReceiptDTO ret= null;
		if(receipt != null) {
			ret = new ReceiptDTO();
			ret.setId(receipt.getId());
			if(receipt.getBusiness() != null) {
				ret.setBusiness(receipt.getBusiness().getName());
			}
			ret.setDate(receipt.getDate());
			ret.setAmount(receipt.getAmount());
			ret.setCreatedAt(receipt.getCreatedAt());
			ret.setTicket(AssemberFacade.toFileDto.apply(receipt.getTicket()));
			ret.setInvoice(AssemberFacade.toInvoiceDto.apply(receipt.getInvoice()));
			if(receipt.getStatus() != null)
				ret.setStatus(receipt.getStatus().toString());
		}
		return ret;
	};
}
