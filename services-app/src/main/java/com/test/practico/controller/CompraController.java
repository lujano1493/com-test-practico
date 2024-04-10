package com.test.practico.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.practico.dto.CompraUpdateDtoRequest;
import com.test.practico.entity.Compra;
import com.test.practico.service.CompraService;

@RestController
@Validated
@RequestMapping("compras/{clienteId}")
public class CompraController {
	@Autowired
	private CompraService compraService;

	@GetMapping(path="cliente")
	public List<Compra> findAllByCliente( @PathVariable("clienteId") Long clienteId){
		return compraService.findAllByClient(clienteId);
	}
	
	@PutMapping
	public  void updateByCliente(@PathVariable("clienteId") Long clienteId ,  @RequestBody @Valid CompraUpdateDtoRequest compraUpdateDtoRequest ) {
		compraService.update(clienteId,compraUpdateDtoRequest);
	}
	
	@DeleteMapping( path="{id}" )
	public void delete(@PathVariable("clienteId")Long clienteId, @PathVariable("id") Long id ) {
		compraService.delete(clienteId,id);
	}
}
