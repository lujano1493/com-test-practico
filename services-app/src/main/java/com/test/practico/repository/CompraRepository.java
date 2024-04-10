package com.test.practico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.practico.entity.Compra;


public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	long deleteByClienteId(long id );

}
