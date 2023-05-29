package com.api.management.backend.models.services;

import org.springframework.http.ResponseEntity;

import com.api.management.backend.models.entity.Alumno;

public interface IAlumnoService {

	public ResponseEntity<?> getAll();
	
	public ResponseEntity<?> getListGrade(int idAlumno);

	public ResponseEntity<?> assingGrade(Integer idAlumno, Integer idMateria, Double calificacion);

	public ResponseEntity<?> changeGrade(Integer idCalificacion, Double calificacion);

	public ResponseEntity<?> deleteGrade(Integer idCalificacion);

	public ResponseEntity<?> getAllMaterias();

	public ResponseEntity<?> getAllAlumnos();
	
	public ResponseEntity<?> save(Alumno alumno);

	public ResponseEntity<?> delete(int id);

	public ResponseEntity<?> getById(int id);

	
}
