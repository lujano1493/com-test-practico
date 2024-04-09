package com.test.practico.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.test.practico.enums.Estatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 15)
	private String clave;
	
	@Column(length = 150)
	private String descripcion;
	
	@Enumerated(EnumType.ORDINAL)
	private Estatus estatus;
	
	@OneToMany( cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "produto")
	@JsonManagedReference
	@ToString.Exclude
	private List<CompraDetalle> detalles;
	
	@PrePersist
	public void beforeSave() {
		if(  estatus==null ) {
			estatus= Estatus.ACTIVO;
		}
	}
	
	
	
}
