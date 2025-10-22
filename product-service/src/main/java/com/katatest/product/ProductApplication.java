package com.katatest.product;


import com.katatest.product.repositoy.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@AllArgsConstructor

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.katatest.product")
@EnableJpaRepositories(basePackages = "com.katatest.product.repository")
public class ProductApplication {

	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	}


