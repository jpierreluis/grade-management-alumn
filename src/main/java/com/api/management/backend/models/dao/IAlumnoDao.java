package com.api.management.backend.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.management.backend.models.entity.Alumno;


public interface IAlumnoDao extends JpaRepository<Alumno, Integer>{
	
	@Query("select a from Alumno a where a.activo=1")
	public List<Alumno> getAlumnsActive();

	
}
