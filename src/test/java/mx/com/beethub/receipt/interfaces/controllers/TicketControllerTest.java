package mx.com.beethub.receipt.interfaces.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import mx.com.beethub.receipt.interfaces.dto.DtoObjectBuilder;
import mx.com.beethub.receipt.interfaces.dto.FileDTO;
import mx.com.beethub.receipt.interfaces.dto.ReceiptDTO;
import mx.com.beethub.receipt.model.DomainObjectBuilder;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TicketControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	void createReceiptTest() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		UriComponentsBuilder builder = UriComponentsBuilder
			.fromUri(new URI("http://localhost:" + port + "/ticket/" + DomainObjectBuilder.account));
		
		FileDTO ticket = DtoObjectBuilder.aNew().createTicket();
		
		HttpEntity<?> entity = new HttpEntity<>(ticket, headers);
		
		ResponseEntity<ReceiptDTO> receiptResponse = this.restTemplate.postForEntity(builder.toUriString(), entity, ReceiptDTO.class);
		
		assertAll("", 
			() -> assertEquals( HttpStatus.OK ,receiptResponse.getStatusCode()),
			() -> assertEquals("IN_PROGRESS", receiptResponse.getBody().getStatus()),
			() -> assertEquals(ticket.getKey(), receiptResponse.getBody().getTicket().getKey())
			
		);
		
	}

}
