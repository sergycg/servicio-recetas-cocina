package com.recetas.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recetas.entity.Receta;


public interface RecetaCocinaDao extends JpaRepository<Receta, Long> {

	@Query("select e from Receta e where e.nombre like %?1% order by e.nombre asc")
	public List<Receta> findByNombre(String term);

	@Query("select e from Receta e where e.nombre like %:nombre% order by e.nombre asc")
	Page<Receta> findByNombre(@Param("nombre") String nombre, Pageable pageable);
	
	public List<Receta> findAllByOrderByNombreAsc(); 
	
	public Page<Receta> findAllByOrderByNombreAsc(Pageable pageable); 

	
}
