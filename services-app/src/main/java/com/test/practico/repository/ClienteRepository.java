package com.test.practico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.practico.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
