package com.api.management.backend.models.services.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.management.backend.models.dao.IMateriaDao;
import com.api.management.backend.models.entity.Alumno;
import com.api.management.backend.models.entity.Materia;
import com.api.management.backend.models.services.IMateriaService;
@Service
public class MateriaServiceImp implements IMateriaService{
	@Autowired
	private IMateriaDao materiaDao;

	@Override
	public ResponseEntity<?> getAll() {
		Map<Object, Object> response = new HashMap<Object, Object>();

		List<Materia> list;
		try {
			list = materiaDao.getMaterias();
		} catch (DataAccessException ex) {
			response.put("message", "Error la consulta no se pudo realizar");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> save(Materia materia) {
		Map<Object, Object> response = new HashMap<Object, Object>();
		try {
			Materia ma = materiaDao.save(materia);
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
	public ResponseEntity<?> delete(int id) {
		Map<Object, Object> response = new HashMap<Object, Object>();
		try {
			Materia materia = materiaDao.findById(id).orElse(null);
			materia.setActivo(0);
			materiaDao.save(materia);
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
		response.put("msg", "Alumno eliminado");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getById(int id) {
		Map<Object, Object> response = new HashMap<Object, Object>();
		Materia materia;
		try {
			materia = materiaDao.findById(id).orElse(null);
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
		response.put("body", materia);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
