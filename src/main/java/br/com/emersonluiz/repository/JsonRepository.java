package br.com.emersonluiz.repository;

import java.util.List;

import br.com.emersonluiz.model.Json;

public interface JsonRepository {

    Json create(Json json);

    Json get(int id) throws Exception;

    void delete(int id) throws Exception;

    List<Json> list(int systemId);
}
