package br.com.emersonluiz.service;

import java.util.List;

import br.com.emersonluiz.model.Entities;

public interface EntityService {

    Entities create(int systemId, Entities entities) throws Exception;

    Entities get(int systemId, int id) throws Exception;

    void delete(int systemId, int id) throws Exception;

    List<Entities> list(int systemId) throws Exception;
}
