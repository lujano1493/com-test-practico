package com.test.practico.dto;

import java.util.List;

import com.test.practico.enums.Estatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteCreateRequest {
	
	
	private String nombre;
	private List<CompraCreateRequest> compras;
	
	//private Estatus estatus;
	

}
