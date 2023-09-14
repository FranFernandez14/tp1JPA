package com.utn.tp1jpa;

import com.utn.tp1jpa.entidades.*;
import com.utn.tp1jpa.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp1jpaApplication {

	@Autowired
	ClienteRepositorio clienteRepositorio;
	@Autowired
	RubroRepositorio rubroRepositorio;
	@Autowired
	ProductoRepositorio productoRepositorio;
	@Autowired
	DetallePedidoRepositorio detallePedidoRepositorio;
	@Autowired
	PedidoRepositorio pedidoRepositorio;
	@Autowired
	FacturaRepositorio facturaRepositorio;
	@Autowired
	DomicilioRepositorio domicilioRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(Tp1jpaApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {

			Producto producto1 = Producto.builder().tipo("manufacturado")
							.tiempoEstimadoDeCocina(20)
							.denominacion("Hamburguesa")
							.precioVenta(150)
							.precioCompra(80)
							.stockActual(100)
							.stockMinimo(20)
							.unidadMedida("gramos")
							.receta("carne, queso, lechuga, tomate y pan")
							.build();

			Producto producto2 = Producto.builder().tipo("manufacturado")
					.tiempoEstimadoDeCocina(10)
					.denominacion("Pizza")
					.precioVenta(200)
					.precioCompra(50)
					.stockActual(30)
					.stockMinimo(20)
					.unidadMedida("gramos")
					.receta("masa, muzzarella, salsa")
					.build();



			List<Producto> productos=new ArrayList<>();
			productos.add(producto1);
			productos.add(producto2);

			Rubro rubro = Rubro.builder().denominacion("Comida rápida")
					.productos(productos)
					.build();

			rubroRepositorio.save(rubro);

			Domicilio domicilio1= Domicilio.builder().calle("Calle oscura")
					.numero("23")
					.localidad("Gotham")
					.build();

			Domicilio domicilio2= Domicilio.builder().calle("Calle no tan oscura")
					.numero("12")
					.localidad("Gotham")
					.build();



			List<Domicilio> domicilios = new ArrayList<>();
			domicilios.add(domicilio1);
			domicilios.add(domicilio2);


			DetallePedido detallePedido1 = DetallePedido.builder().cantidad(1)
							.producto(producto1)
							.subtotal(producto1.getPrecioVenta())
							.build();

			DetallePedido detallePedido2 = DetallePedido.builder().cantidad(1)
					.producto(producto2)
					.subtotal(producto2.getPrecioVenta())
					.build();



			List<DetallePedido> detallesPedido = new ArrayList<>();
			detallesPedido.add(detallePedido1);
			detallesPedido.add(detallePedido2);

			Factura factura= Factura.builder().numero(777)
							.fecha(new Date())
							.descuento(0)
							.formaPago("Efectivo")
							.total(350)
							.build();




			Pedido pedido1= Pedido.builder().estado("En preparación")
							.fecha(new Date())
							.tipoEnvio("Delivery")
							.detalles(detallesPedido)
							.factura(factura)
							.build();



			List<Pedido> pedidos = new ArrayList<>();
			pedidos.add(pedido1);

			Cliente cliente1= Cliente.builder().nombre("Batman")
					.apellido("murciélago")
					.telefono("desconocido")
					.email("batmanmurcielago@gmail.com")
					.domicilios(domicilios)
					.pedidos(pedidos)
					.build();
			/*
			productoRepositorio.save(producto1);
			productoRepositorio.save(producto2);

			 */

			/*
			domicilioRepositorio.save(domicilio1);
			domicilioRepositorio.save(domicilio2);
			detallePedidoRepositorio.save(detallePedido1);
			detallePedidoRepositorio.save(detallePedido2);
			facturaRepositorio.save(factura);
			pedidoRepositorio.save(pedido1);
			 */
			clienteRepositorio.save(cliente1);

			List<Cliente> clientes= clienteRepositorio.findAll();

			for (Cliente cliente: clientes){
				System.out.println(cliente.getNombre());
			}
		};
	}
}