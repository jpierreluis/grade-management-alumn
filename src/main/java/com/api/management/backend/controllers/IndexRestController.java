package com.api.management.backend.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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

import com.api.management.backend.commons.JasperReportManager;
import com.api.management.backend.enums.TipoReporteEnum;
import com.api.management.backend.models.entity.dto.ReporteDto;
import com.api.management.backend.models.entity.vo.ListGrade;
import com.api.management.backend.models.services.IAlumnoService;
import com.api.management.backend.models.services.IReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api")
public class IndexRestController {

	@Autowired
	private IAlumnoService alumnoService;
	
	@Autowired
	private IReportService reportService;

	//private final Logger logger = LoggerFactory.getLogger(IndexRestController.class);
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
	public ResponseEntity<?> changeGrade(
			@RequestParam(name = "id_t_calificaciones") Integer idCalificacion,
			@RequestParam(name = "calificacion") Double calificacion) {
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

}
