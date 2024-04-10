package com.test.practico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.practico.dto.ClienteCreateDtoRequest;
import com.test.practico.entity.Cliente;
import com.test.practico.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping
	public void save(@RequestBody @Valid ClienteCreateDtoRequest clienteCreateDtoRequest){
		 clienteService.save(clienteCreateDtoRequest);
		
	}
	@ResponseStatus(code = HttpStatus.OK )
	@GetMapping(path="{id}")
	public Cliente findById(@PathVariable("id") Long id  ) {
		return clienteService.findById(id);
		
	}

}
