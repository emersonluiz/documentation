package br.com.emersonluiz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonluiz.exception.BadRequestException;
import br.com.emersonluiz.exception.NotFoundException;
import br.com.emersonluiz.model.Entities;
import br.com.emersonluiz.model.Json;
import br.com.emersonluiz.model.Rest;
import br.com.emersonluiz.repository.EntityRepository;
import br.com.emersonluiz.repository.JsonRepository;
import br.com.emersonluiz.repository.RestRepository;

@Service
public class DefaultRestService implements RestService {

    private RestRepository restRepository;
    private EntityRepository entityRepository;
    private JsonRepository jsonRepository;
    private static final Logger logger = LoggerFactory.getLogger(DefaultRestService.class);

    @Autowired
    public DefaultRestService(RestRepository restRepository, EntityRepository entityRepository, JsonRepository jsonRepository) {
        this.restRepository = restRepository;
        this.entityRepository = entityRepository;
        this.jsonRepository = jsonRepository;
    }

    @Override
    public Rest create(int entityId, Rest rest) throws Exception {
        Entities entities = entityRepository.get(entityId);
        rest.setEntities(entities);
        if (rest.getRequestJson() != null) {
            try {
                Json requestJson = jsonRepository.get(rest.getRequestJson().getId());
                rest.setRequestJson(requestJson);
            } catch (NotFoundException e) {
                logger.error("Request JSON id: " + rest.getRequestJson().getId() + " is invalid");
                throw new BadRequestException("Request JSON id: " + rest.getRequestJson().getId() + " is invalid");
            }
        }
        if (rest.getResponseJson() != null) {
            try {
                Json responseJson = jsonRepository.get(rest.getResponseJson().getId());
                rest.setResponseJson(responseJson);
            } catch (NotFoundException e) {
                logger.error("Response JSON id: " + rest.getResponseJson().getId() + " is invalid");
                throw new BadRequestException("Response JSON id: " + rest.getResponseJson().getId() + " is invalid");
            }
        }
        restRepository.create(rest);
        return rest;
    }

    @Override
    public Rest get(int entityId, int id) throws Exception {
        entityRepository.get(entityId);
        Rest rest = restRepository.get(id);
        return rest;
    }

    @Override
    public void delete(int entityId, int id) throws Exception {
        entityRepository.get(entityId);
        restRepository.delete(id);
    }

    @Override
    public List<Rest> list(int entityId) throws Exception {
        entityRepository.get(entityId);
        List<Rest> list = restRepository.list(entityId);
        return list;
    }

}
