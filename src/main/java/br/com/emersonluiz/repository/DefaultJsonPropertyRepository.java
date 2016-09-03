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
import br.com.emersonluiz.model.JsonProperty;

@Transactional
@Repository
public class DefaultJsonPropertyRepository implements JsonPropertyRepository {

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(DefaultJsonPropertyRepository.class);

    @Override
    public JsonProperty create(JsonProperty jsonProperty) {
        try {
            entityManager.persist(jsonProperty);
            logger.debug("Repository : JSON Property was created");
            return jsonProperty;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public JsonProperty get(int id) throws Exception {
        try {
            JsonProperty jsonProperty = entityManager.find(JsonProperty.class, id);
            if (jsonProperty == null) {
                throw new NotFoundException("JSON Property id: " + id + " was not found.");
            }
            logger.debug("Repository : JSON Property was found");
            return jsonProperty;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            JsonProperty jsonProperty = get(id);
            entityManager.remove(jsonProperty);
            logger.debug("Repository : JSON Property was deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<JsonProperty> list(int jsonId) {
        try {
            Query query = entityManager.createQuery("SELECT jp FROM JsonProperty jp INNER JOIN jp.parentJson j WHERE j.id = :jsonId");
            query.setParameter("jsonId", jsonId);
            @SuppressWarnings("unchecked")
            List<JsonProperty> list = query.getResultList();
            logger.debug("Repository : All JSON Properties were listed.");
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
