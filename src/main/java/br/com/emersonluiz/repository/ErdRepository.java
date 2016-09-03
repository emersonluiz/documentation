package br.com.emersonluiz.repository;

import java.util.List;

import br.com.emersonluiz.model.Erd;

public interface ErdRepository {

    Erd create(Erd erd);

    Erd get(int id) throws Exception;

    void delete(int id) throws Exception;

    List<Erd> list(int entityId);
    
}
