package br.com.emersonluiz.repository;

import java.util.List;

import br.com.emersonluiz.model.System;

public interface SystemRepository {

    System create(System system);

    System get(int id) throws Exception;

    void delete(int id) throws Exception;

    List<System> list();
}
