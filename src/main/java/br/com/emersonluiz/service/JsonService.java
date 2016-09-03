package br.com.emersonluiz.service;

import java.util.List;

import br.com.emersonluiz.model.Json;

public interface JsonService {

    Json create(int systemId, Json json) throws Exception;

    Json get(int systemId, int id) throws Exception;

    void delete(int systemId, int id) throws Exception;

    List<Json> list(int systemId) throws Exception;
}
