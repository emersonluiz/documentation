package br.com.emersonluiz.repository;

import java.util.List;

import br.com.emersonluiz.model.Entities;

public interface EntityRepository {

    Entities create(Entities entities);

    Entities get(int id) throws Exception;

    void delete(int id) throws Exception;

    List<Entities> list(int systemId);

}
