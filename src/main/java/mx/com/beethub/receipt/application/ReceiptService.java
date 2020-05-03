package mx.com.beethub.receipt.application;

import java.util.List;

import mx.com.beethub.receipt.model.entity.Receipt;
import mx.com.beethub.receipt.model.value.File;

public interface ReceiptService {
	
	public Receipt createFromTicket(String account, File ticket);
	public List<Receipt> find(String account, String filter);
	
}
