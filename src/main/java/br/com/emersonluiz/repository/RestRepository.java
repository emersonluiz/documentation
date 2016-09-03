package br.com.emersonluiz.repository;

import java.util.List;

import br.com.emersonluiz.model.Rest;

public interface RestRepository {

    Rest create(Rest rest);

    Rest get(int id) throws Exception;

    void delete(int id) throws Exception;

    List<Rest> list(int entityId);

}
