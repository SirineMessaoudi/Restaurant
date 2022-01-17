package com.ds_JEE_M_H.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds_JEE_M_H.Entities.Met;

public interface MetRepository extends JpaRepository<Met, Long>{
	public Optional<Met> findByNom(String nom);
}
