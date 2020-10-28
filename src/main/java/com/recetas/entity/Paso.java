package com.recetas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="paso")
public class Paso implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7858580383054665115L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="titulo_paso")
	private String tituloPaso;

	@Column(name="descripcion_paso")
	private String descripcionPaso;
	
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "pasos", "ingredientes", 
//		"titulo", "descripcion", "observaciones", "createAt" })
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	private Receta receta;

	public Receta getReceta() {
		return receta;
	}


	public void setReceta(Receta receta) {
		this.receta = receta;
	}


	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	
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


	public String getTituloPaso() {
		return tituloPaso;
	}


	public void setTituloPaso(String tituloPaso) {
		this.tituloPaso = tituloPaso;
	}


	public String getDescripcionPaso() {
		return descripcionPaso;
	}


	public void setDescripcionPaso(String descripcionPaso) {
		this.descripcionPaso = descripcionPaso;
	}



	
}
