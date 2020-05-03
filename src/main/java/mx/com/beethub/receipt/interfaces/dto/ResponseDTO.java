package mx.com.beethub.receipt.interfaces.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseDTO<T> {
	
	private List<T> data;

}
