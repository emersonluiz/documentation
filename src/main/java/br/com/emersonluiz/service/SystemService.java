package br.com.emersonluiz.service;

import java.util.List;

import br.com.emersonluiz.model.System;

public interface SystemService {

    System create(System system);

    System get(int id) throws Exception;

    void delete(int id) throws Exception;

    List<System> list();
}
