package com.recetas.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recetas.common.controller.CommonController;
import com.recetas.entity.Receta;
import com.recetas.service.RecetaCocinaService;
import com.recetas.service.UploadFileService;

//@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/recetas")
public class RecetasCocinaController extends CommonController<Receta, RecetaCocinaService>{
	
	
	@Autowired
	private RecetaCocinaService recetaCocinaService;
	@Autowired
	@Qualifier("UploadFile")
	private UploadFileService uploadFileService;

	@GetMapping()
	public ResponseEntity<?> getRecetas() {
		List<Receta> recetas = recetaCocinaService.findAllSortByAsc("nombre");
		if (recetas != null) {
			return new ResponseEntity<>(recetas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrarRecetas(@PathVariable String term){
		return ResponseEntity.ok(recetaCocinaService.findLikeNombre(term));
	}
	
	@GetMapping("/pagina/filtrar/{term}")
	public ResponseEntity<?> filtrarPaginable(Pageable pageable, @PathVariable String term){
		Page<Receta> recetas = recetaCocinaService.findLikeNombre(term, pageable);
		if (recetas != null) {
			return new ResponseEntity<>(recetas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> getBynombre(@PathVariable String nombre) {
		List<Receta> recetas = recetaCocinaService.findByNombre(nombre);
		if (recetas != null) {
			return new ResponseEntity<>(recetas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Receta receta = recetaCocinaService.findById(id);
			String nombreFotoAnterior = receta.getFotoPortada();
			uploadFileService.eliminar(nombreFotoAnterior);
			recetaCocinaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/imagen/{nombreFoto}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Resource recurso = null;
		
		try {
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

	}
	
	@PostMapping("/imagen/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		Receta receta = recetaCocinaService.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadFileService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen de la receta");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = receta.getFotoPortada();
			uploadFileService.eliminar(nombreFotoAnterior);
			receta.setFotoPortada(nombreArchivo);
			recetaCocinaService.save(receta);
			
			response.put("receta", receta);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	
	
//	@PostMapping("/getRecetaById_bis")
//	public ResponseEntity<?> getRecetaById_bis(@RequestBody String id) {
//		Receta receta = recetaCocinaService.findRecetaById(new Long(id));
//		if (receta != null) {
//			return new ResponseEntity<>(receta, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

	
	
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
