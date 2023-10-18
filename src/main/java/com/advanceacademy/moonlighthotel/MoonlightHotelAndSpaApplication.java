package com.advanceacademy.moonlighthotel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Moonlight APIs",version = "1.0",description = "Hotel management APIs"))
public class MoonlightHotelAndSpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonlightHotelAndSpaApplication.class, args);
	}

}
