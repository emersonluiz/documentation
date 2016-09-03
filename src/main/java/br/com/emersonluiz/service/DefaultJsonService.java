package br.com.emersonluiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonluiz.model.System;
import br.com.emersonluiz.model.Json;
import br.com.emersonluiz.repository.JsonRepository;
import br.com.emersonluiz.repository.SystemRepository;

@Service
public class DefaultJsonService implements JsonService {

    private JsonRepository jsonRepository;
    private SystemRepository systemRepository;

    @Autowired
    public DefaultJsonService(JsonRepository jsonRepository, SystemRepository systemRepository) {
        this.jsonRepository = jsonRepository;
        this.systemRepository = systemRepository;
    }

    @Override
    public Json create(int systemId, Json json) throws Exception {
        System system = systemRepository.get(systemId);
        json.setSystem(system);
        jsonRepository.create(json);
        return json;
    }

    @Override
    public Json get(int systemId, int id) throws Exception {
        systemRepository.get(systemId);
        Json json = jsonRepository.get(id);
        return json;
    }

    @Override
    public void delete(int systemId, int id) throws Exception {
        systemRepository.get(systemId);
        jsonRepository.delete(id);
    }

    @Override
    public List<Json> list(int systemId) throws Exception {
        systemRepository.get(systemId);
        List<Json> list = jsonRepository.list(systemId);
        return list;
    }

}
