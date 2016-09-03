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
import br.com.emersonluiz.model.Erd;

@Transactional
@Repository
public class DefaultErdRepository implements ErdRepository {

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(DefaultErdRepository.class);

    @Override
    public Erd create(Erd erd) {
        try {
            entityManager.persist(erd);
            logger.debug("Repository : ERD was created");
            return erd;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Erd get(int id) throws Exception {
        try {
            Erd erd = entityManager.find(Erd.class, id);
            if (erd == null) {
                throw new NotFoundException("ERD id: " + id + " was not found.");
            }
            logger.debug("Repository : ERD was found");
            return erd;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Erd erd = get(id);
            entityManager.remove(erd);
            logger.debug("Repository : ERD was deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Erd> list(int entityId) {
        try {
            Query query = entityManager.createQuery("SELECT erd FROM Erd erd INNER JOIN erd.entities e WHERE e.id = :entityId");
            query.setParameter("entityId", entityId);
            @SuppressWarnings("unchecked")
            List<Erd> list = query.getResultList();
            logger.debug("Repository : All ERD's were listed.");
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
