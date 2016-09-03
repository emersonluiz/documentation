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
import br.com.emersonluiz.model.Entities;

@Transactional
@Repository
public class DefaultEntityRepository implements EntityRepository {

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(DefaultEntityRepository.class);

    @Override
    public Entities create(Entities entities) {
        try {
            entityManager.persist(entities);
            logger.debug("Repository : Entities was created");
            return entities;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Entities get(int id) throws Exception {
        try {
            Entities entities = entityManager.find(Entities.class, id);
            if (entities == null) {
                throw new NotFoundException("Entities id: " + id + " was not found.");
            }
            logger.debug("Repository : Entities was found");
            return entities;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Entities entities = get(id);
            entityManager.remove(entities);
            logger.debug("Repository : Entities was deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Entities> list(int systemId) {
        try {
            Query query = entityManager.createQuery("SELECT e FROM Entities e INNER JOIN e.system s WHERE s.id = :systemId");
            query.setParameter("systemId", systemId);
            @SuppressWarnings("unchecked")
            List<Entities> list = query.getResultList();
            logger.debug("Repository : All Entities were listed.");
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
