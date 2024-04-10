package com.test.practico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.practico.entity.CompraDetalle;
import com.test.practico.entity.CompraDetalleId;

public interface CompraDetalleRepository extends  JpaRepository<CompraDetalle, CompraDetalleId >{


}
