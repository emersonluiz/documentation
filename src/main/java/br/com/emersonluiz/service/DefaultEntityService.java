package br.com.emersonluiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonluiz.model.Entities;
import br.com.emersonluiz.model.System;
import br.com.emersonluiz.repository.EntityRepository;
import br.com.emersonluiz.repository.SystemRepository;

@Service
public class DefaultEntityService implements EntityService {

    private EntityRepository entityRepository;
    private SystemRepository systemRepository;

    @Autowired
    public DefaultEntityService(EntityRepository entitiesRepository, SystemRepository systemRepository) {
        this.entityRepository = entitiesRepository;
        this.systemRepository = systemRepository;
    }

    @Override
    public Entities create(int systemId, Entities entities) throws Exception {
        System system = systemRepository.get(systemId);
        entities.setSystem(system);
        entityRepository.create(entities);
        return entities;
    }

    @Override
    public Entities get(int systemId, int id) throws Exception {
        systemRepository.get(systemId);
        Entities entities = entityRepository.get(id);
        return entities;
    }

    @Override
    public void delete(int systemId, int id) throws Exception {
        systemRepository.get(systemId);
        entityRepository.delete(id);
    }

    @Override
    public List<Entities> list(int systemId) throws Exception {
        systemRepository.get(systemId);
        List<Entities> list = entityRepository.list(systemId);
        return list;
    }

}
