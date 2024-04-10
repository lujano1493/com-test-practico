package com.test.practico.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.test.practico.enums.Estatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (  strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column( length = 50)
	private String nombre;
	
	private Date fechaRegistro;
	
	private Date fechaUltimaActualizacion;
	

	@Enumerated(EnumType.ORDINAL)
	private Estatus estatus;
	
	@ManyToOne(  optional = false ,fetch = FetchType.LAZY )
	@JoinColumn(name="cliente_id")
	@JsonBackReference
	@ToString.Exclude
	private Cliente cliente;
	
	
	@OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "compra")
	@JsonManagedReference
	@ToString.Exclude
	private List<CompraDetalle> detalles;
	
	

	@PrePersist
	public void beforeSave() {
		if( fechaRegistro == null ) {
			fechaRegistro= new Date();
		}
		if( fechaUltimaActualizacion == null ) {
			fechaUltimaActualizacion= new Date();
		}
		if( estatus == null ) {
			estatus= Estatus.ACTIVO;
		}
		
	}
	
	@PreUpdate
	public void beforeUpdate() {
		fechaUltimaActualizacion= new Date();
	}
	

}
