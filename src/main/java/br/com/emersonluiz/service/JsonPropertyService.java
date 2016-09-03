package br.com.emersonluiz.service;

import java.util.List;

import br.com.emersonluiz.model.JsonProperty;

public interface JsonPropertyService {

    JsonProperty create(int jsonId, JsonProperty json) throws Exception;

    JsonProperty get(int jsonId, int id) throws Exception;

    void delete(int jsonId, int id) throws Exception;

    List<JsonProperty> list(int jsonId) throws Exception;
}
