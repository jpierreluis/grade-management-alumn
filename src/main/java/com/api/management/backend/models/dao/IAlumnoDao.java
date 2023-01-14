package com.api.management.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.management.backend.models.entity.Alumno;


public interface IAlumnoDao extends JpaRepository<Alumno, Integer>{

}
