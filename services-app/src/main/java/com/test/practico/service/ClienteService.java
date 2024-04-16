package com.test.practico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.test.practico.dto.ClienteCreateDtoRequest;
import com.test.practico.entity.Cliente;
import com.test.practico.enums.Estatus;
import com.test.practico.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CompraService compraService;
	
	

	@Transactional
	public void save( ClienteCreateDtoRequest clienteCreateDtoRequest ){
		
		Cliente cliente = Cliente.builder()
				.nombre(clienteCreateDtoRequest.getNombre())
				.estatus(Estatus.ACTIVO)
				.build();
		Long id= clienteCreateDtoRequest.getId();
		if(id !=null ) {
			if( !clienteRepository.existsById(id) ) {
				throw new ResponseStatusException( HttpStatus.NOT_FOUND ,"cliente no encontrado");
			}
			cliente=clienteRepository.findById(id).get();
		}
		
		clienteRepository.save(cliente);
		compraService.saveCompras(cliente, clienteCreateDtoRequest.getCompras()  );
		
	}
	
	@Transactional
	public void delete(Long id) {
		if( !clienteRepository.existsById( id) ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente no encontrado"  );
		}
		
		clienteRepository.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		
		return clienteRepository.findById(id).orElseThrow( 
				 ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")   
				 );
	}

}
