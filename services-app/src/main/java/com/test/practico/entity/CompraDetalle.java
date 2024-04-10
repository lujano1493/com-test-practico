package com.test.practico.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompraDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	@JsonIgnore
	@EmbeddedId
	private CompraDetalleId id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name ="compra_id")
	@MapsId("compraId")
	@JsonBackReference
	@ToString.Exclude
	private Compra compra;
	
	@ManyToOne(optional = false ,fetch = FetchType.EAGER)
	@JoinColumn(name="producto_id")
	@MapsId("productoId")
	@JsonManagedReference
	@ToString.Exclude
	private Producto produto;

	private Long cantidad;
	
}
