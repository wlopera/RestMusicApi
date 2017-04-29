package com.music.services.impl;

import com.music.domain.Artista;
import com.music.domain.Musico;
import com.music.services.ServiciosRest;
import com.music.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Session;

public class ServiciosRestImpl implements ServiciosRest {

	public Musico consultarArtista(Integer id) {
		Musico musico = null;
		try {
			HibernateUtil.getSessionFactory();				
			HibernateUtil.openSessionAndBindToThread();		// Abrir un hilo de conexion
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			Artista artista =(Artista)session.get(Artista.class,id);
			
			DozerBeanMapper dozer = new DozerBeanMapper();
			List<String> lista = new ArrayList<String>();
			lista.add("dozerMapping.xml");
			dozer.setMappingFiles(lista);
			musico =  dozer.map(artista, Musico.class);
			
			System.out.println("##=> Artista[Base de datos]: "+artista);
			System.out.println("##=> Musico [Transformado dozer]: "+musico);
			 			
	     } catch (Exception e){
	    	 System.out.println("Error Consultando artista: " + e.getMessage());
		 } finally {
             HibernateUtil.closeSessionAndUnbindFromThread();  // Cerrar un hilo de conexion   		
         }
        return musico;
	}

	public int crearArtista(Artista artista) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int borrar(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
