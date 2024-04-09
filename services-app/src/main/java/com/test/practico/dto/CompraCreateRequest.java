package com.test.practico.dto;

import java.util.List;

import com.test.practico.enums.Estatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompraCreateRequest {
	
	private List<CompraDetalleCreateRequest> detalles;
	
	private String nombre;
	//private Estatus estatus;
	
}
