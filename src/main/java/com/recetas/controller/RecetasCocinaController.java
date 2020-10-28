package com.recetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.entity.Ingrediente;
import com.recetas.entity.Receta;
import com.recetas.service.IRecetaCocinaService;

@RestController
@RequestMapping("/RecetasCocina")
public class RecetasCocinaController {

	@RequestMapping("/help")
	public String helloWorld() {
		return "Hola mundo!!!!!!";
	}

	@Autowired
	private IRecetaCocinaService recetaCocinaService;

	@GetMapping("/getRecetas")
	public ResponseEntity<?> getRecetas() {
		List<Receta> recetas = recetaCocinaService.findRecetas();
		if (recetas != null) {
			return new ResponseEntity<>(recetas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getRecetaById/{id}")
	public ResponseEntity<?> getRecetaById(@PathVariable Long id) {
		Receta receta = recetaCocinaService.findRecetaById(id);
		if (receta != null) {
			return new ResponseEntity<>(receta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/getRecetaById_bis")
	public ResponseEntity<?> getRecetaById_bis(@RequestBody String id) {
		Receta receta = recetaCocinaService.findRecetaById(new Long(id));
		if (receta != null) {
			return new ResponseEntity<>(receta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/saveReceta")
	public ResponseEntity<?> saveReceta(@RequestBody Receta receta) {
		this.recetaCocinaService.saveReceta(receta);			
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteReceta/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReceta(@PathVariable Long id) {
		this.recetaCocinaService.deleteReceta(id);
	}

	@GetMapping("/getIngredientes")
	public ResponseEntity<?> getIngredientes() {
		List<Ingrediente> ingrediente = recetaCocinaService.findIngredientes();
		if (ingrediente != null) {
			return new ResponseEntity<>(ingrediente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getIngredienteById/{id}")
	public ResponseEntity<?> getIngredienteById(@PathVariable Long id) {
		Ingrediente ingrediente = recetaCocinaService.findIngredienteById(id);
		if (ingrediente != null) {
			return new ResponseEntity<>(ingrediente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/saveIngrediente")
	public ResponseEntity<?> saveIngrediente(@RequestBody Ingrediente ingrediente) {
		this.recetaCocinaService.saveIngrediente(ingrediente);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/deleteIngrediente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIngrediente(@PathVariable Long id) {
		this.recetaCocinaService.deleteIngrediente(id);
	}


	
	/*
	 * @GetMapping("/mpmflow/list/{json}")
	 * 
	 * @ResponseStatus(value = HttpStatus.OK) public JsonObject
	 * listMPMFLow(@PathVariable String json) throws MamFlowException {
	 * LOGGER.debug(messagesUtils.getLog(LoggerConstants.DEBUG_CONTROLLER_REQUEST),
	 * "GET", json, "listMPMFLow"); JsonObject deserializedJson =
	 * Jsoner.deserialize(json, new JsonObject()); return
	 * tarsysService.listMPMFLow(deserializedJson); }
	 * 
	 * @PostMapping(value = "/mpmflow/queue")
	 * 
	 * @ResponseStatus(value = HttpStatus.OK) public JsonObject
	 * queueMPMFlow(@RequestBody String json) throws MamFlowException {
	 * LOGGER.debug(messagesUtils.getLog(LoggerConstants.DEBUG_CONTROLLER_REQUEST),
	 * "POST", json, "queueMPMFlow"); JsonObject deserializedJson =
	 * Jsoner.deserialize(json, new JsonObject()); return
	 * tarsysService.queueMPMFlow(deserializedJson); }
	 * 
	 * 
	 * 
	 */

}
