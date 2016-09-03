package br.com.emersonluiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonluiz.repository.SystemRepository;
import br.com.emersonluiz.model.System;

@Service
public class DefaultSystemService implements SystemService {

    private SystemRepository systemRepository;

    @Autowired
    public DefaultSystemService(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public System create(System system) {
        systemRepository.create(system);
        return system;
    }

    @Override
    public System get(int id) throws Exception {
        System system = systemRepository.get(id);
        return system;
    }

    @Override
    public void delete(int id) throws Exception {
        systemRepository.delete(id);
    }

    @Override
    public List<System> list() {
        List<System> list = systemRepository.list();
        return list;
    }
}
