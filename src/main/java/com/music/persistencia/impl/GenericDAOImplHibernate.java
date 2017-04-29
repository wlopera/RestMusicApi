package com.music.persistencia.impl;

import com.music.exception.BussinessException;
import com.music.exception.BussinessMessage;
import com.music.persistencia.GenericDAO;
import com.music.util.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericDAOImplHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {
	  
	  
    SessionFactory sessionFactory;

    private final static Logger LOGGER = Logger.getLogger(GenericDAOImplHibernate.class.getName());

    public GenericDAOImplHibernate() {
        sessionFactory=HibernateUtil.getSessionFactory();
   }

   public T create() throws BussinessException {
       try {
           return getEntityClass().newInstance();
       } catch (InstantiationException ex) {
           throw new RuntimeException(ex);
       } catch (RuntimeException ex) {
           throw ex;
       } catch (Exception ex) {
           throw new RuntimeException(ex);
       }
   }

   public void saveOrUpdate(T entity) throws BussinessException {
       Session session = sessionFactory.getCurrentSession();
       try {
           session.beginTransaction();
           session.saveOrUpdate(entity);
           session.getTransaction().commit();    
       } catch (org.hibernate.exception.ConstraintViolationException cve) {
           try {
               if (session.getTransaction().isActive()) {
                   session.getTransaction().rollback();
               }
           } catch (Exception exc) {
               LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
           }
           throw new BussinessException(cve);
       } catch (RuntimeException ex) {
           try {
               if (session.getTransaction().isActive()) {
                   session.getTransaction().rollback();
               }
           } catch (Exception exc) {
               LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
           }
           throw ex;
       } catch (Exception ex) {
           try {
        	   if (session.getTransaction().isActive()) {
                   session.getTransaction().rollback();
               }
           } catch (Exception exc) {
               LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
           }
           throw new RuntimeException(ex);
       }
   }

   public T get(ID id) throws BussinessException {
       Session session = sessionFactory.getCurrentSession();
       try {
           session.beginTransaction();
           T entity = (T) session.get(getEntityClass(), id);
           session.getTransaction().commit();

           return entity;
       } catch (org.hibernate.exception.ConstraintViolationException cve) {
           try {
               if (session.getTransaction().isActive()) {
                   session.getTransaction().rollback();
               }
           } catch (Exception exc) {
               LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
           }
           throw new BussinessException(cve);
      } catch (RuntimeException ex) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw ex;
      } catch (Exception ex) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw new RuntimeException(ex);
      }
  }

  public void delete(ID id) throws BussinessException {
      Session session = sessionFactory.getCurrentSession();
      try {
          session.beginTransaction();
          T entity = (T) session.get(getEntityClass(), id);
          if (entity == null) {
              throw new BussinessException(new BussinessMessage(null, "Los datos a borrar ya no existen"));
          }
          session.delete(entity);
          session.getTransaction().commit();
      } catch (org.hibernate.exception.ConstraintViolationException cve) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw new BussinessException(cve);
      } catch (BussinessException ex) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw ex;
      } catch (RuntimeException ex) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw ex;
      } catch (Exception ex) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw new RuntimeException(ex);
      }
  }

  public List<T> findAll() throws BussinessException {
      Session session = sessionFactory.getCurrentSession();
      try {

          Query query = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
          List<T> entities = query.list();

          return entities;
      } catch (org.hibernate.exception.ConstraintViolationException cve) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw new BussinessException(cve);
      } catch (RuntimeException ex) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw ex;
      } catch (Exception ex) {
          try {
              if (session.getTransaction().isActive()) {
                  session.getTransaction().rollback();
              }
          } catch (Exception exc) {
              LOGGER.log(Level.WARNING,"Fall� al hacer un rollback", exc);
          }
          throw new RuntimeException(ex);
      }
  }

  @SuppressWarnings("unchecked")
private Class<T> getEntityClass() {
      return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }
}