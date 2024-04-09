package com.test.practico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.practico.dto.ClienteCreateRequest;
import com.test.practico.dto.CompraCreateRequest;
import com.test.practico.dto.CompraDetalleCreateRequest;
import com.test.practico.dto.ProductoCreateRequest;
import com.test.practico.dto.ResponseDto;
import com.test.practico.entity.Cliente;
import com.test.practico.entity.Compra;
import com.test.practico.entity.CompraDetalle;
import com.test.practico.entity.Producto;
import com.test.practico.repository.ClienteRepository;
import com.test.practico.repository.CompraRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	public ResponseEntity<ResponseDto> save( ClienteCreateRequest clienteRequest ){
		
		Cliente cliente = Cliente.builder()
				.nombre(clienteRequest.getNombre())
			//	.compras( fromDtoToCompras(clienteRequest.getCompras()) )
				.build();
		

		clienteRepository.save(cliente);
		List<Compra> compras = fromDtoToCompras(clienteRequest.getCompras());
		
		compras.forEach( compra-> compra.setCliente(cliente)  );
		
		compraRepository.saveAll(compras);
		
		return ResponseEntity.ok( new ResponseDto("cliente creado", 0)  );
		
	}

	private List<Compra> fromDtoToCompras(List<CompraCreateRequest> compras) {
		return compras.stream().map(  dtoCompra ->  {
			return  Compra.builder()
						.nombre( dtoCompra.getNombre() )
						.detalles(  fromDtoToDetalles( dtoCompra.getDetalles() ) ).build();
		}    ).toList() ;
	}

	private List<CompraDetalle> fromDtoToDetalles(List<CompraDetalleCreateRequest> detalles) {

		return   detalles.stream().map( dtoDetalle ->{
			
			return CompraDetalle.builder().cantidad( dtoDetalle.getCantidad())
					.produto( fromDtoToProducto(  dtoDetalle.getProducto())  )
					.build();
			
		}   ).toList() ;
	}

	private Producto fromDtoToProducto(ProductoCreateRequest  producto) {
		
		return Producto.builder().clave(producto.getClave()).descripcion(producto.getDescripcion()).build();
	}
}
