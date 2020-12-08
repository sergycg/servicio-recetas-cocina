package com.recetas.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="recetas")
public class Receta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5101489325275400152L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	
	private String descripcion;
	
	private String observaciones;
	
	@Column(name="foto_portada")
	private String fotoPortada;

	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

//	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnoreProperties({ "receta" })
	@OneToMany(mappedBy="receta", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="receta_id",referencedColumnName="id")
	private List<RecetaIngredientes> ingredientes;

	
	@JsonIgnoreProperties({ "receta" })
	@OneToMany(mappedBy="receta", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="receta_id",referencedColumnName="id")
	private List<Paso> pasos = new ArrayList<>();

	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<RecetaIngredientes> getIngredientes() {
		return ingredientes;
	}


	public void setIngredientes(List<RecetaIngredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public List<Paso> getPasos() {
		return pasos;
	}


	public void setPasos(List<Paso> pasos) {
		this.pasos = pasos;
	}


	public String getFotoPortada() {
		return fotoPortada;
	}


	public void setFotoPortada(String fotoPortada) {
		this.fotoPortada = fotoPortada;
	}
	
	
	
}
