package mx.com.beethub.receipt.application.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.beethub.receipt.application.ReceiptService;
import mx.com.beethub.receipt.model.entity.Receipt;
import mx.com.beethub.receipt.model.value.File;
import mx.com.beethub.receipt.model.value.ReceiptStatus;
import mx.com.beethub.receipt.repository.ReceiptRepository;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

	private ReceiptRepository receiptRepository;
	
	@Autowired
	public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
		this.receiptRepository = receiptRepository;
	}
	
	@Override
	public Receipt createFromTicket(String account, File ticket) {
		Receipt r = new Receipt();
		Receipt ret = null;
		r.setTicket(ticket);
		r.setAccount(account);
		r.setStatus(ReceiptStatus.IN_PROGRESS);
		ret = this.receiptRepository.save(r);
		
		return ret;
	}

	@Override
	public List<Receipt> find(String account, String filter) {
		if(filter != null && filter.trim().length() > 0) {
			return this.findWithFilter(account, filter);
		}else {
			return this.receiptRepository.findByAccount(account);
		}
	}

	private List<Receipt> findWithFilter(String account, String filter) {
		String filterWords[] = filter.trim().split(" ");
		String filterWithOperator = Arrays.stream(filterWords).collect(Collectors.joining(" +"));
		String filterWithAccount = "+" + account + " +" + filterWithOperator;
		log.debug("Filter: {}", filterWithAccount);
		List<Receipt> receipts = this.receiptRepository.findReceiptsByFilter(filterWithAccount)
				.parallelStream()
				.filter(r -> r.getAccount().equals(account))
				.collect(Collectors.toList());
		log.trace("Receipts: {}", receipts);
		return receipts;
	}
}
