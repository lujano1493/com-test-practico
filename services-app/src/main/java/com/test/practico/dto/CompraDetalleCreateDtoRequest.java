package com.test.practico.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompraDetalleCreateDtoRequest {
	
	@NotNull(message = "ingresa un producto")
	private ProductoCreateDtoRequest producto;
	@Min(value = 1 , message = "ingresa una cantidad mayor a 0")
	@NotNull(message = "ingresa una cantidad")
	private Long cantidad;

}
