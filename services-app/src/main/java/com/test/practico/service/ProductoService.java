package com.test.practico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.test.practico.dto.ProductoCreateDtoRequest;
import com.test.practico.entity.Producto;
import com.test.practico.repository.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository productoRepository;

	public void save(ProductoCreateDtoRequest productoDtoRequest) {
		_save(productoDtoRequest,true);
	}
	
	public void update(ProductoCreateDtoRequest productoDtoRequest) {
		_save(productoDtoRequest,false);

	}
	@Transactional
	private void _save(ProductoCreateDtoRequest productoDtoRequest,boolean isNew) {
		if(  !isNew ) {
			if(productoDtoRequest.getId() ==null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ingresa el id de producto");
			}
			if(productoDtoRequest.getId() != null) {
				if( !productoRepository.existsById( productoDtoRequest.getId())) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "producto no encontrado");
				}
			}
		}
		Producto producto= createEntityFromDto(productoDtoRequest,isNew);
		productoRepository.save(producto);
	}
	public Producto getProducto(ProductoCreateDtoRequest productoDtoRequest) {
		
		Producto producto = createEntityFromDto(productoDtoRequest, true );
		
		if (productoDtoRequest.getId() != null) {
			producto = findByIdOrThowException(productoDtoRequest.getId());
		} 
		return producto;
		
	}
	@Transactional(readOnly = true)
	private Producto findByIdOrThowException(Long id) {
		return productoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("Producto con %d no encontrado", id)));
	}

	private Producto createEntityFromDto(ProductoCreateDtoRequest productoDto, boolean isNew) {
		Producto producto = Producto.builder()
				.clave(productoDto.getClave())
				.descripcion(productoDto.getDescripcion())
				.estatus(productoDto.getEstatus())
				.build();
		if(!isNew && productoDto.getId()!=null){
			producto.setId(productoDto.getId());
		}
		return producto;
		
	}

	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	public Producto findById(Long id) {
		return findByIdOrThowException(id);
	}

}
