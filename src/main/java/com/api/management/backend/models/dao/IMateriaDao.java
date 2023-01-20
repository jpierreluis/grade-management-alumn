package com.api.management.backend.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.management.backend.models.entity.Materia;


public interface IMateriaDao extends JpaRepository<Materia, Integer>{
	@Query(value = "select m from Materia m where "
			+ "activo=1")
	public List<Materia> getMaterias();
}
