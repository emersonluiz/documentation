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
import br.com.emersonluiz.model.Rest;

@Transactional
@Repository
public class DefaultRestRepository implements RestRepository {

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(DefaultRestRepository.class);

    @Override
    public Rest create(Rest rest) {
        try {
            entityManager.persist(rest);
            logger.debug("Repository : Rest was created");
            return rest;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Rest get(int id) throws Exception {
        try {
            Rest rest = entityManager.find(Rest.class, id);
            if (rest == null) {
                throw new NotFoundException("Rest id: " + id + " was not found.");
            }
            logger.debug("Repository : Rest was found");
            return rest;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Rest rest = get(id);
            entityManager.remove(rest);
            logger.debug("Repository : Rest was deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Rest> list(int entityId) {
        try {
            Query query = entityManager.createQuery("SELECT r FROM Rest r INNER JOIN r.entities e WHERE e.id = :entityId");
            query.setParameter("entityId", entityId);
            @SuppressWarnings("unchecked")
            List<Rest> list = query.getResultList();
            logger.debug("Repository : All Rest's were listed.");
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
