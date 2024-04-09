package com.test.practico.dto;

import com.test.practico.enums.Estatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoCreateRequest {
	
	private String clave;
	private String descripcion;
//	private Estatus estatus;
	
}
