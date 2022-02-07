package com.babyfix.babyfix.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.babyfix.babyfix.models.Veiculo;

public interface VeiculoRepository extends CrudRepository<Veiculo, String>{
	Veiculo findByCodigo(long codigo);
	List<Veiculo> findByMarca(String marca);
}
