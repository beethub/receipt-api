package mx.com.beethub.receipt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Map;


@SpringBootApplication
@EnableFeignClients
public class ReceiptApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Map<String, String> env = System.getenv();
		env.forEach((k, v) -> System.out.println(k + ":" + v));
	}
}
