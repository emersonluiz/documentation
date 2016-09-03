package br.com.emersonluiz.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.emersonluiz.exception.NotFoundException;
import br.com.emersonluiz.model.System;

@Transactional
@Repository
public class DefaultSystemRepository implements SystemRepository {

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(DefaultSystemRepository.class);

    @Override
    public System create(System system) {
        try {
            entityManager.persist(system);
            logger.debug("Repository : System was created");
            return system;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public System get(int id) throws Exception {
        try {
            System system = entityManager.find(System.class, id);
            if (system == null) {
                throw new NotFoundException("System id: " + id + " was not found.");
            }
            logger.debug("Repository : System was found");
            return system;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(int id) throws Exception {
       try {
           System system = get(id);
           entityManager.remove(system);
           logger.debug("Repository : System was deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<System> list() {
        try {
            Query query = entityManager.createQuery("FROM System");
            @SuppressWarnings("unchecked")
            List<System> list = query.getResultList();
            logger.debug("Repository : All System's were listed.");
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
