package br.com.emersonluiz.service;

import java.io.InputStream;

public interface GenerateService {

    InputStream generateHtml(int systemId) throws Exception;
}
