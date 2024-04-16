package com.test.practico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.test.practico.dto.CompraCreateDtoRequest;
import com.test.practico.dto.CompraDetalleCreateDtoRequest;
import com.test.practico.dto.CompraUpdateDtoRequest;
import com.test.practico.dto.ProductoCreateDtoRequest;
import com.test.practico.entity.Cliente;
import com.test.practico.entity.Compra;
import com.test.practico.entity.CompraDetalle;
import com.test.practico.entity.CompraDetalleId;
import com.test.practico.entity.Producto;
import com.test.practico.enums.Estatus;
import com.test.practico.repository.ClienteRepository;
import com.test.practico.repository.CompraDetalleRepository;
import com.test.practico.repository.CompraRepository;

@Service
public class CompraService {
	@Autowired
	private CompraRepository compraRepository;
	
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public void update(Long clienteId,CompraUpdateDtoRequest compraUpdateDtoRequest ) {
		if( !clienteRepository.existsById(clienteId)  ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente no encontrado");
		}
		Cliente cliente = clienteRepository.findById(clienteId).get();
		
		saveCompras(cliente, compraUpdateDtoRequest.getCompras());
	}
	
	public void saveCompras(Cliente cliente, List<CompraCreateDtoRequest> comprasDto) {
		
		if(  cliente.getCompras() !=null) {
			   // Eliminar todas las compras asociadas al cliente
	        cliente.getCompras().forEach( (compra)->{
	        	//compra.setCliente(null);
	        	//compra.getDetalles().forEach(  d -> d.setCompra(null)  );
	        	compraRepository.delete(compra)	;
	        });

	      
		}
		
		
		comprasDto.forEach(compraDto ->{
			Compra compra = Compra.builder()
					.estatus(Estatus.ACTIVO)
					.cliente(cliente)
					.nombre(compraDto.getNombre())
					.detalles(new ArrayList<CompraDetalle>())
					.build();
			List<CompraDetalleCreateDtoRequest> compraDetallesDto= compraDto.getDetalles();
			for(CompraDetalleCreateDtoRequest compraDetalleDto : compraDetallesDto  ) {
				ProductoCreateDtoRequest productoDto= compraDetalleDto.getProducto();
				
				Producto producto = productoService.getProducto(productoDto);
				
				CompraDetalle compraDetalle = CompraDetalle.builder()
						.cantidad(compraDetalleDto.getCantidad())
						.compra(compra)
						.produto(producto)
						.id(  new CompraDetalleId(  )  )
						.build();
			
				
				compra.getDetalles().add(compraDetalle);
			}
			compraRepository.save(compra);
		
		}  );
		
	}

	public List<Compra> findAllByClient(Long clienteId) {
		return  getClienteById(clienteId).
				getCompras();
	}

	private Cliente getClienteById(Long clienteId) {
		return clienteRepository.
				findById(clienteId).
				orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente no encontrado" ) );
	}

	public void delete(Long clienteId,Long id) {
		
		Cliente cliente = getClienteById(clienteId);
		Compra compra= cliente.getCompras()
		.stream()
		.filter( c -> c.getId() == id  )
		.findFirst()
		.orElseThrow(  ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "compra no encontrada") );
		compraRepository.deleteById(compra.getId()  );
	}
}
