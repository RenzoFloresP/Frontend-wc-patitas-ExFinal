package pe.edu.cibertec.patita_frontend_wc_a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PatitaFrontendWcAApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatitaFrontendWcAApplication.class, args);
	}

}
