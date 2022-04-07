package com.example.laptop;

import com.example.laptop.entity.Laptop;
import com.example.laptop.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LaptopApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LaptopApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		// Creamos Laptops
		Laptop laptop1 = new Laptop(null, "ASUS", "Rog Strix", 15, "Ryzen 7", 16, 500, 2.2, 799.99, true);
		Laptop laptop2 = new Laptop(null, "Lenovo", "IdeaPad", 15, "I5", 8, 256, 2.9, 458.59, false);

		// Se almacenan en el repositorio
		repository.save(laptop1);
		repository.save(laptop2);

		// Mostramos la cantidad de la BBDD
		System.out.println("Num laptops en BBDD:\t" + repository.count());

	}
}
