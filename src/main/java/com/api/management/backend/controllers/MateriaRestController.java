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
import com.api.management.backend.models.entity.Materia;
import com.api.management.backend.models.services.IMateriaService;

@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api/materia")
public class MateriaRestController {

	@Autowired
	private IMateriaService materiaService;

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		return materiaService.getAll();
	}
	
	@GetMapping("/id")
	public ResponseEntity<?> getById(@RequestParam(name="id") int id) {
		return materiaService.getById(id);
	}

	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Materia materia) {
		return materiaService.save(materia);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> delete(@RequestParam(name="id") int id) {
		return materiaService.delete(id);
	}

	
}
