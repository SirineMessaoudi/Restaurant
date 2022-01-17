package com.ds_JEE_M_H.Repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds_JEE_M_H.Entities.Table;

public interface TableRepository extends JpaRepository<Table, Long>{
	 public Optional<Table> findByNumero(int numero);
}
