package com.utn.tp1jpa;

import com.utn.tp1jpa.entidades.Cliente;
import com.utn.tp1jpa.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Tp1jpaApplication {

	@Autowired
	ClienteRepositorio clienteRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(Tp1jpaApplication.class, args);
	}


	Cliente cliente = new Cliente();

	@Bean
	CommandLineRunner init(ClienteRepositorio clienteRepositorio) {
		return args -> {
			Cliente cliente1= Cliente.builder().nombre("hola").build();

			clienteRepositorio.save(cliente1);

			List<Cliente> clientes= clienteRepositorio.findAll();


			for (Cliente cliente: clientes){
				System.out.println(cliente.getNombre());
			}
		};
	}
}