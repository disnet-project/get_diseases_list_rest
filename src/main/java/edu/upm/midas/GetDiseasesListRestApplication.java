package edu.upm.midas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@SpringBootApplication
public class GetDiseasesListRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetDiseasesListRestApplication.class, args);
	}
}
