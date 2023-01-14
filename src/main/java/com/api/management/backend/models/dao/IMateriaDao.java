package com.api.management.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.management.backend.models.entity.Materia;


public interface IMateriaDao extends JpaRepository<Materia, Integer>{

}
