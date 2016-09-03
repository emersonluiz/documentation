package br.com.emersonluiz.repository;

import java.util.List;

import br.com.emersonluiz.model.JsonProperty;

public interface JsonPropertyRepository {

    JsonProperty create(JsonProperty jsonProperty);

    JsonProperty get(int id) throws Exception;

    void delete(int id) throws Exception;

    List<JsonProperty> list(int jsonId);
}
