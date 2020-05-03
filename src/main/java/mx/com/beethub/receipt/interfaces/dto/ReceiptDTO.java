package mx.com.beethub.receipt.interfaces.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel( description = "details about the receipt" )
public class ReceiptDTO {
	@ApiModelProperty(notes = "Unique of the receipt")
	private long id;
	private String business;
	private LocalDate date;
	private Long amount;
	private LocalDateTime createdAt;
	private FileDTO ticket;
	private InvoiceDTO invoice;
	private String status;
}
