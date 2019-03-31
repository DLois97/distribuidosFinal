package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ModeloMonedas;


public interface MonedasRepository extends JpaRepository<ModeloMonedas,Long>{
	ModeloMonedas findById(long id);
	List<ModeloMonedas> findByMetal(String metal);
}
