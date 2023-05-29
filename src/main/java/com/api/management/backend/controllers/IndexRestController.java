package com.api.management.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.management.backend.models.services.IAlumnoService;
import com.api.management.backend.models.services.IReportService;



@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api")
public class IndexRestController {

	@Autowired
	private IAlumnoService alumnoService;
	
	@Autowired
	private IReportService reportService;

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		return alumnoService.getAll();
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> index(@PathVariable int id) {
		return alumnoService.getListGrade(id);
	}

	@PostMapping("/")
	public ResponseEntity<?> assigGrade(
			@RequestParam(name = "id_t_usuario") Integer idAlumno,
			@RequestParam(name = "id_t_materias") Integer idMateria,
			@RequestParam(name = "calificacion") Double calificacion) {
		
		return alumnoService.assingGrade(idAlumno,idMateria,calificacion);
	}

	@PutMapping("/")
	public ResponseEntity<?> change(
			@RequestParam(name = "id_t_calificaciones") Integer idCalificacion,
			@RequestParam(name = "calificacion") Double calificacion
			) {
		return alumnoService.changeGrade(idCalificacion,calificacion);
	}

	@DeleteMapping("/")
	public ResponseEntity<?> deleteGrade(
			@RequestParam(name = "id_t_calificaciones") Integer idCalificacion) {
		return alumnoService.deleteGrade(idCalificacion);
	}

	
	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> pdf(){
		return reportService.generatePdf();
	}
	/*
	@GetMapping("/materias")
	public ResponseEntity<?> getAllMaterias() {
		return alumnoService.getAllMaterias();
	}
	
	@GetMapping("/alumnos")
	public ResponseEntity<?> getAllAlumnos() {
		return alumnoService.getAllAlumnos();
	} * 
	 * */
	
	


}
