package com.test.practico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.practico.dto.ClienteCreateRequest;
import com.test.practico.dto.ResponseDto;
import com.test.practico.entity.Cliente;
import com.test.practico.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity< ResponseDto> save(@RequestBody @Valid ClienteCreateRequest clienteRequest){
		return clienteService.save(clienteRequest);
		
	}

}
