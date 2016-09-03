package br.com.emersonluiz.service;

import java.io.InputStream;
import java.util.List;

import br.com.emersonluiz.model.Erd;

public interface ErdService {

    Erd create(int entityId, Erd erd, InputStream inputStream) throws Exception;

    Erd get(int entityId, int id) throws Exception;

    void delete(int entityId, int id) throws Exception;

    List<Erd> list(int entityId);

}
