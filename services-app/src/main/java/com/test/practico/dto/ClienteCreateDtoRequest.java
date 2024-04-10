package com.test.practico.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteCreateDtoRequest {
	
	private Long id;
	@NotEmpty(message="ingresa un nombre")
	private String nombre;
	@NotEmpty(message = "ingresa una lista de compras")
	private List<CompraCreateDtoRequest> compras;
	
	//private Estatus estatus;
	

}
