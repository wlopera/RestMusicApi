package com.music.controller;

import com.music.domain.Artista;
import com.music.domain.Musico;
import com.music.domain.Profesor;
import com.music.services.ServiciosRest;
import com.music.services.impl.ServiciosRestImpl;
import com.music.util.HibernateUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RestController {

	@RequestMapping(value = "/movie/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {

		model.addAttribute("movie", name);
		return "list";

	}

	@RequestMapping(value = "/movie/", method = RequestMethod.GET)
	public String getDefaultMovie(ModelMap model) {

		model.addAttribute("movie", "this is default movie");
		return "list";

	}
	
	@RequestMapping(value = "/variable/{data}", method = RequestMethod.GET)
	public void getMessage2(@PathVariable String data, WebRequest request,
			HttpServletResponse response) throws JsonGenerationException,
			JsonMappingException, IOException {

		try {
			ObjectMapper mapper = new ObjectMapper();

			Integer id = Integer.valueOf(data);
	        Profesor profesor = consultarProfesor(id);
			if (profesor == null){
				convertResponse("No existe persona para ese ID ["+data+"]", request, response);
			} else {
				String jsonString = mapper.writeValueAsString(profesor);

				System.out.println("Profesor: " + profesor.toString());

				convertResponse(jsonString, request, response);

			}
		} catch (NumberFormatException nfe){
			convertResponse("Dato de entrada ["+data+"] debe ser un número ", request, response);
		} catch (Exception e){
			//System.out.println(e);
			convertResponse("Error en la base de datos...!", request, response);
		}
	}

	@RequestMapping(value = "/music/{data}", method = RequestMethod.GET)
	public void getMusic(@PathVariable String data, WebRequest request,
			HttpServletResponse response) throws JsonGenerationException,
			JsonMappingException, IOException {

		try {
			ObjectMapper mapper = new ObjectMapper();

			Integer id = Integer.valueOf(data);
			ServiciosRest servicio = new ServiciosRestImpl();
	        Musico musico = servicio.consultarArtista(id);
	        
			if (musico == null){
				convertResponse("No existe artista para ese ID ["+data+"]", request, response);
			} else {				
				
				String jsonString = mapper.writeValueAsString(musico);
				convertResponse(jsonString, request, response);

			}
		} catch (NumberFormatException nfe){
			convertResponse("Dato de entrada ["+data+"] debe ser un número ", request, response);
		} catch (Exception e){
			System.out.println(e.getMessage());
			convertResponse("Error en la base de datos...!", request, response);
		}
	}

	private void convertResponse(String jsonString, WebRequest request,
			HttpServletResponse response) {

		AbstractHttpMessageConverter<String> stringHttpMessageConverter = new StringHttpMessageConverter();
		
		MediaType mimeType = MediaType.parseMediaType("text/plain;charset=UTF-8");
		if (stringHttpMessageConverter.canWrite(String.class, mimeType)) {
			try {
				
				stringHttpMessageConverter.write(jsonString, mimeType,
						new ServletServerHttpResponse(response));
				
							            
             
			} catch (IOException m_Ioe) {
			} catch (HttpMessageNotWritableException p_Nwe) {
			}
		}

	}
	
	public Profesor consultarProfesor(Integer id){
		Profesor profesor = null;
		try {
			HibernateUtil.getSessionFactory();				
			HibernateUtil.openSessionAndBindToThread();		// Abrir un hilo de conexion
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			profesor =(Profesor)session.get(Profesor.class,id);
	     } catch (Exception e){
	    	 System.out.println("Error Consultando profesor: " + e.getMessage());
		 } finally {
             HibernateUtil.closeSessionAndUnbindFromThread();  // Cerrar un hilo de conexion   		
         }
        return profesor;
	}
	
	public Artista consultarArtista(Integer id){
		Artista artista = null;
		try {
			HibernateUtil.getSessionFactory();				
			HibernateUtil.openSessionAndBindToThread();		// Abrir un hilo de conexion
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			artista =(Artista)session.get(Artista.class,id);
	     } catch (Exception e){
	    	 System.out.println("Error Consultando artista: " + e.getMessage());
		 } finally {
             HibernateUtil.closeSessionAndUnbindFromThread();  // Cerrar un hilo de conexion   		
         }
        return artista;
	}

}