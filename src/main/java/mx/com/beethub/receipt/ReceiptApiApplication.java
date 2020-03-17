package mx.com.beethub.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReceiptApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptApiApplication.class, args);
	}

}
