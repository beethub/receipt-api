package mx.com.beethub.receipt.interfaces.dto;

import lombok.Data;

@Data
public class InvoiceDTO {
	private FileDTO xml;
	private FileDTO pdf;
}
