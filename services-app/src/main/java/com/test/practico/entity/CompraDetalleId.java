package com.test.practico.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CompraDetalleId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4362709991957060097L;
	private Long compraId;
	private Long productoId;

}
