package com.api.management.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.management.backend.models.entity.Alumno;
import com.api.management.backend.models.services.IAlumnoService;

@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api/alumno")
public class AlumnoRestController {
	
	@Autowired
	private IAlumnoService alumnoService;

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		return alumnoService.getAllAlumnos();
	}
	
	@GetMapping("/id")
	public ResponseEntity<?> getById(@RequestParam(name="id") int id) {
		return alumnoService.getById(id);
	}

	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Alumno alumno) {
		return alumnoService.save(alumno);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> delete(@RequestParam(name="id") int id) {
		return alumnoService.delete(id);
	}

	
}
