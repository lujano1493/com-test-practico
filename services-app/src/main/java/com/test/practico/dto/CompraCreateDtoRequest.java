package com.test.practico.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.test.practico.enums.Estatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompraCreateDtoRequest {
	@NotEmpty(message="ingresa una lista de detalles")
	private List<CompraDetalleCreateDtoRequest> detalles;
	@NotEmpty(message="ingresa un nombre")
	private String nombre;
	//private Estatus estatus;
	
}
