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
import br.com.emersonluiz.model.Json;

@Transactional
@Repository
public class DefaultJsonRepository implements JsonRepository {

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(DefaultJsonRepository.class);

    @Override
    public Json create(Json json) {
        try {
            entityManager.persist(json);
            logger.debug("Repository : JSON was created");
            return json;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Json get(int id) throws Exception {
        try {
            Json json = entityManager.find(Json.class, id);
            if (json == null) {
                throw new NotFoundException("JSON id: " + id + " was not found.");
            }
            logger.debug("Repository : JSON was found");
            return json;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Json json = get(id);
            entityManager.remove(json);
            logger.debug("Repository : JSON was deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Json> list(int systemId) {
        try {
            Query query = entityManager.createQuery("SELECT j FROM Json j INNER JOIN j.system s WHERE s.id = :systemId");
            query.setParameter("systemId", systemId);
            @SuppressWarnings("unchecked")
            List<Json> list = query.getResultList();
            logger.debug("Repository : All JSON's were listed.");
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
