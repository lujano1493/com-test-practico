package com.test.practico.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompraUpdateDtoRequest {
	@NotEmpty(message = "ingresa lista de compras")
	private List<CompraCreateDtoRequest> compras;
}
