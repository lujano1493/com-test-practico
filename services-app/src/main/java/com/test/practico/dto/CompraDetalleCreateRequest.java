package com.test.practico.dto;

import java.util.List;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompraDetalleCreateRequest {
	
	
	private ProductoCreateRequest producto;
	
	private Long cantidad;

}
