package mx.com.beethub.receipt.interfaces.dto;

import mx.com.beethub.receipt.model.DomainObjectBuilder;

public class DtoObjectBuilder {
	
	public static DtoObjectBuilder aNew() {
        return new DtoObjectBuilder();
    }
	
	public FileDTO createTicket() {
		FileDTO ret = new FileDTO();
		ret.setKey(DomainObjectBuilder.tiket_key);
		return ret;
	}
	
	
}
