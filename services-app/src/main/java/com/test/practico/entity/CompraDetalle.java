package com.test.practico.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
public class CompraDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8735687194545612468L;
	@Id
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY ,optional = false )
	@JsonBackReference
	@ToString.Exclude
	private Compra compra;
	@Id
	@ManyToOne(cascade = CascadeType.ALL,fetch =FetchType.LAZY, optional = false )
	@JsonBackReference
	@ToString.Exclude
	private Producto produto;

	private Long cantidad;
	
}
