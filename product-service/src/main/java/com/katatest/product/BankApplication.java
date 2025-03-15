package com.katatest.product;

import com.katatest.product.enteties.InventoryStatus;
import com.katatest.product.enteties.Product;
import com.katatest.product.repositoy.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor

@SpringBootApplication
public class BankApplication {

	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}


	@Bean
	public ApplicationRunner applicationRunner() throws IOException {
		RestTemplate restTemplate = new RestTemplate();

		String imagePath = "c:/photo/s5.jpg"; // Modifie le chemin
		byte[] imageData = Files.readAllBytes(Path.of(imagePath));


		Product product = Product.builder()
				.code("1code")
				.category("accessoire")
				.name("casque")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.internalReference("ref1")
				.rating(11)
				.quantity(10)
				.image(imageData)
				.build();
		productRepository.save(product);


		Product product2 = Product.builder()
				.code("2code")
				.category("besoin")
				.name("casque")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.internalReference("ref2")
				.rating(11)
				.quantity(5)
				.image(imageData)
				.build();

		productRepository.save(product2);
		List<Product> strProduct = productRepository.findAll();
		strProduct.forEach(System.out::println);

		return args -> {
			System.out.println("Exécution personnalisée avec ApplicationRunner !");
		};
	}
}


