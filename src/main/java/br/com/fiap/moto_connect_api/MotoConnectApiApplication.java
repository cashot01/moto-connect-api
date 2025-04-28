package br.com.fiap.moto_connect_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "MotoConnect API", version = "v1", description = "API do projeto Moto Connect", contact = @Contact(name = "Cauan Passos", email = "cauan@motoConnect.com")))
public class MotoConnectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoConnectApiApplication.class, args);
	}

}
