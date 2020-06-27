package com.recetas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.recetas.entity.Receta;


public interface IRecetaCocinaDao extends JpaRepository<Receta, Long> {


	
}
