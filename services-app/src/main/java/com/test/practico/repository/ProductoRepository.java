package com.test.practico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.practico.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
