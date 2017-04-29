package com.music.test;

import com.music.domain.Profesor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Run {
	public static void main (String[] args){
        new Run().init();
	}
	
	public void init(){
		//Profesor profesor = agregarProfesor(102, "Carlos", "González", "Oltra");
		//Profesor  profesor = actualizarProfesor(102, "Carlos", "Alberto", "Pareira");
		Profesor profesor = null;
        //deleteProfesor(profesor);
        for (int i=101; 103>i; i++){
        	profesor = consultarProfesor(i);
        }
	}
	
	public Profesor agregarProfesor(Integer id, String nombre, String ape1, String ape2){
		Configuration configuration = new Configuration();
        configuration.configure();
        
        System.out.println("##=> configuration: "+configuration.toString());
        
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Profesor profesor=new Profesor(id, nombre, ape1, ape2);
		Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(profesor);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        return profesor;
	}
	
	public Profesor actualizarProfesor(Integer id, String nombre, String ape1, String ape2){
		Configuration configuration = new Configuration();
        configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();        
		Profesor profesor=new Profesor(id, nombre, ape1, ape2);
		session.beginTransaction();
        session.update(profesor);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        return profesor;
	}
	
	public Profesor consultarProfesor(Integer id){		
		Configuration configuration = new Configuration();
        configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();		
		Session session=sessionFactory.openSession();
		
		Profesor profesor =(Profesor)session.get(Profesor.class,id);
		System.out.println(profesor.getId());
        System.out.println(profesor.getNombre());
        System.out.println(profesor.getApe1());
        System.out.println(profesor.getApe2());   
        session.close();
        sessionFactory.close();
        return profesor;
	}
	
	public void deleteProfesor(Profesor profesor){		
		Configuration configuration = new Configuration();
        configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.delete(profesor);
        session.close();
        sessionFactory.close();       
	}
}
