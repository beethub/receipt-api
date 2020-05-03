package mx.com.beethub.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@EnableSwagger2
public class ReceiptApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptApiApplication.class, args);
	}

}
