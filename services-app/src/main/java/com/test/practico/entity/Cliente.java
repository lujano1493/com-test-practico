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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3519785194223686441L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	private Estatus estatus;
	@Column(  length = 50)
	private String nombre;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "cliente" ,fetch = FetchType.LAZY ,orphanRemoval = true)
	@JsonManagedReference
	@ToString.Exclude
	private List<Compra> compras;
	
	@PrePersist
	public void beforeSave() {
		if(  estatus==null ) {
			estatus= Estatus.ACTIVO;
		}		
		log.info("before save cliente");
		
	}

}
