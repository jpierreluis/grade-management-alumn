package com.api.management.backend.models.services.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.api.management.backend.models.dao.IAlumnoDao;
import com.api.management.backend.models.dao.ICalificacionDao;
import com.api.management.backend.models.dao.IMateriaDao;
import com.api.management.backend.models.entity.Alumno;
import com.api.management.backend.models.entity.Calificacion;
import com.api.management.backend.models.entity.Materia;
import com.api.management.backend.models.entity.vo.ListGrade;
import com.api.management.backend.models.entity.vo.Promedio;
import com.api.management.backend.models.services.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private IAlumnoDao alumnoDao;

	@Autowired
	private ICalificacionDao calificacionDao;

	@Autowired
	private IMateriaDao materiaDao;

	@Override
	public ResponseEntity<?> getAll() {
		Map<Object, Object> response = new HashMap<Object, Object>();

		List<Alumno> list;

		try {
			list = alumnoDao.findAll();
			List<Object> lista = new ArrayList<Object>();
			for (Alumno alumno : list) {
				
				double promedio = 0d;
				for (Calificacion item : alumno.getCalificaciones()) {
					ListGrade calificacion = new ListGrade();
					calificacion.setId(alumno.getId());
					calificacion.setName(alumno.getNombre());
					calificacion.setLastname(alumno.getApPaterno() + " " + alumno.getApPaterno());
					calificacion.setMatter(item.getMateria().getNombre());
					calificacion.setGrade(item.getCalificacion());
					calificacion.setIdCalificacion(item.getId());
					calificacion.setRegistrationDate(item.getFechaRegistroFormateada());
					lista.add(calificacion);
					promedio = promedio + item.getCalificacion();
				}

				Promedio p = new Promedio();
				p.setPromedio((promedio / alumno.getCalificaciones().size()));
				lista.add(p);
			}

		} catch (DataAccessException ex) {
			response.put("message", "Error la consulta no se pudo realizar");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getListGrade(int idAlumno) {
		Map<Object, Object> response = new HashMap<Object, Object>();
		Alumno element;
		List<Object> lista = new ArrayList<Object>();
		try {
			element = alumnoDao.findById(idAlumno).orElse(null);
			double promedio = 0d;
			for (Calificacion item : element.getCalificaciones()) {
				ListGrade calificacion = new ListGrade();
				calificacion.setId(element.getId());
				calificacion.setName(element.getNombre());
				calificacion.setLastname(element.getApPaterno() + " " + element.getApPaterno());
				calificacion.setMatter(item.getMateria().getNombre());
				calificacion.setGrade(item.getCalificacion());
				calificacion.setIdCalificacion(item.getId());
				calificacion.setRegistrationDate(item.getFechaRegistroFormateada());
				lista.add(calificacion);
				promedio = promedio + item.getCalificacion();
			}

			Promedio p = new Promedio();
			p.setPromedio((promedio / element.getCalificaciones().size()));
			lista.add(p);

		} catch (DataAccessException ex) {
			response.put("message", "Error la consulta no se pudo realizar");
			response.put("error", ex.getMessage());
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			response.put("message", "Error la consulta no se pudo realizar");
			response.put("error", ex.getMessage());
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(lista, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> assingGrade(Integer idAlumno, Integer idMateria, Double calificacion) {
		Map<Object, Object> response = new HashMap<Object, Object>();
		try {
			if (idAlumno == null) {
				response.put("success", "Faild");
				response.put("msg", "Introduce un alumno");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (idMateria == null) {
				response.put("success", "Faild");
				response.put("msg", "Introduce una materia");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (!(calificacion >= 0 && calificacion <= 10)) {
				response.put("success", "Faild");
				response.put("msg", "La calificacion tiene un rango entre 0 y 10");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			Calificacion existCal = calificacionDao.getCalificacionByMateriaAndAlumn(idAlumno, idMateria);
			Alumno alumno = alumnoDao.findById(idAlumno).orElse(null);
			Materia materia = materiaDao.findById(idMateria).orElse(null);

			if (existCal != null) {
				response.put("success", "Faild");
				response.put("msg", "La materia ya se ha asignado al alumno");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (alumno == null) {
				response.put("success", "Faild");
				response.put("msg", "El alumno no existe");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (materia == null || materia.getActivo() == 0) {
				response.put("success", "Faild");
				response.put("msg", "La materia que indicaste no existe");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			Calificacion cal = new Calificacion();

			cal.setAlumno(alumno);
			cal.setMateria(materia);
			cal.setFechaRegistro(new Date());
			cal.setCalificacion(calificacion);

			calificacionDao.save(cal);

		} catch (DataAccessException ex) {
			response.put("success", "Faild");
			response.put("msg", ex.getMessage());
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			response.put("success", "Faild");
			response.put("msg", ex.getMessage());
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("success", "OK");
		response.put("msg", "Calificacion registrada");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> changeGrade(Integer idCalificacion, Double calificacion) {
		Map<Object, Object> response = new HashMap<Object, Object>();
		try {

			if (!(calificacion >= 0 && calificacion <= 10)) {
				response.put("success", "Faild");
				response.put("msg", "La calificacion tiene un rango entre 0 y 10");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			Calificacion cal = calificacionDao.findById(idCalificacion).orElse(null);

			if (cal == null) {
				response.put("success", "Faild");
				response.put("msg", "La calificacion no se encontro");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			cal.setCalificacion(calificacion);

			calificacionDao.save(cal);

		} catch (DataAccessException ex) {
			response.put("success", "Faild");
			response.put("msg", ex.getMessage());
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			response.put("success", "Faild");
			response.put("msg", ex.getMessage());
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("success", "OK");
		response.put("msg", "calificacion actualizada");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteGrade(Integer idCalificacion) {
		Map<Object, Object> response = new HashMap<Object, Object>();
		try {

			Calificacion cal = calificacionDao.findById(idCalificacion).orElse(null);

			if (cal == null) {
				response.put("success", "Faild");
				response.put("msg", "La calificacion no se encontro");
				return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			calificacionDao.delete(cal);
		} catch (DataAccessException ex) {
			response.put("success", "Faild");
			response.put("msg", ex.getMessage());
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			response.put("success", "Faild");
			response.put("msg", ex.getMessage());
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("success", "OK");
		response.put("msg", "calificacion actualizada");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
