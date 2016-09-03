package br.com.emersonluiz.service;

import java.util.List;

import br.com.emersonluiz.model.Rest;

public interface RestService {

    Rest create(int entityId, Rest rest) throws Exception;

    Rest get(int entityId, int id) throws Exception;

    void delete(int entityId, int id) throws Exception;

    List<Rest> list(int entityId) throws Exception;

}
