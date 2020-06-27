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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	private String titulo;
	
	private String descripcion;
	
	private String observaciones;

	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="receta_id",referencedColumnName="id")
	private List<RecetaIngredientes> ingredientes;

	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="receta_id",referencedColumnName="id")
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	
	
	
}
