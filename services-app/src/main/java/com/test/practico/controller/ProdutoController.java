package com.test.practico.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.practico.dto.ProductoCreateDtoRequest;
import com.test.practico.entity.Producto;
import com.test.practico.service.ProductoService;

@RestController
@RequestMapping("productos")
public class ProdutoController {
	@Autowired
	private ProductoService productoService;
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping
	public void save(@Valid @RequestBody ProductoCreateDtoRequest productoCreateDtoRequest  ) {
		productoService.save(productoCreateDtoRequest);
	}
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping
	public void update(@Valid @RequestBody ProductoCreateDtoRequest productoCreateDtoRequest ) {
		productoService.update(productoCreateDtoRequest);
		
	}
	@GetMapping
	public List<Producto> findAll(){
		return productoService.findAll();
	}
	
	@GetMapping(path="{id}")
	public Producto findById( @PathVariable Long id ){
		return productoService.findById(id);
	}
}
