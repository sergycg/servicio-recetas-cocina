package com.recetas.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("UploadFileS3")
public class UploadFileS3ServiceImpl implements UploadFileService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${configuracion.s3.url}")
	private String s3_url;
	
	@Value("${configuracion.s3.accion.upload}")
	private String s3_accion_upload;
	
	@Value("${configuracion.s3.accion.download}")
	private String s3_accion_download;
	
	@Value("${configuracion.s3.accion.delete}")
	private String s3_accion_delete;
	
	private final static String DIRECTORIO_STATIC_IMAGES = "src\\main\\resources\\static\\images";
	private final static String DEFAULT_IMAGE = "camara-de-fotos.png";

	
	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {

		try {
			ResponseEntity<byte[]> response = restTemplate.exchange(
					getPath(s3_accion_download, nombreFoto), HttpMethod.GET, null,
					byte[].class);
			return new ByteArrayResource(response.getBody());
		} catch (Exception e) {
			Path rutaArchivo = Paths.get(DIRECTORIO_STATIC_IMAGES).resolve(DEFAULT_IMAGE).toAbsolutePath();
			return new UrlResource(rutaArchivo.toUri());
		}

	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {

		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file", new HttpEntity<>(archivo.getResource()));
		map.add("fileName", new HttpEntity<String>(nombreArchivo));
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				getPath(s3_accion_upload, ""), HttpMethod.POST, request,
				String.class);

		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		boolean estado = false;
		try {
			if (nombreFoto != null && nombreFoto.length() > 0) {
				ResponseEntity<String> response = restTemplate.exchange(
						getPath(s3_accion_delete, nombreFoto), HttpMethod.GET,
						null, String.class);

				estado = true;

			}
		} catch (Exception e) {
			estado = false;
		}
		return estado;
	}

	private String getPath(String accion, String nombreFoto) {
		String url = s3_url + accion + (nombreFoto==null?"":nombreFoto);
		return url;
	}
}
