package br.com.emersonluiz.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonluiz.repository.ErdRepository;
import br.com.emersonluiz.repository.EntityRepository;
import br.com.emersonluiz.repository.JsonPropertyRepository;
import br.com.emersonluiz.repository.JsonRepository;
import br.com.emersonluiz.repository.RestRepository;
import br.com.emersonluiz.repository.SystemRepository;
import br.com.emersonluiz.util.DocumentFile;
import br.com.emersonluiz.util.HtmlDocumentation;
import br.com.emersonluiz.util.Zip;
import br.com.emersonluiz.model.Erd;
import br.com.emersonluiz.model.Entities;
import br.com.emersonluiz.model.Json;
import br.com.emersonluiz.model.JsonProperty;
import br.com.emersonluiz.model.Rest;
import br.com.emersonluiz.model.System;

@Service
public class DefaultGenerateService implements GenerateService {

    private SystemRepository systemRepository;
    private EntityRepository entityRepository;
    private JsonRepository jsonRepository;
    private JsonPropertyRepository jsonPropertyRepository;
    private RestRepository restRepository;
    private ErdRepository erdRepository;

    @Autowired
    public DefaultGenerateService(SystemRepository systemRepository,
            EntityRepository entityRepository, JsonRepository jsonRepository,
            JsonPropertyRepository jsonPropertyRepository,
            RestRepository restRepository, ErdRepository erdRepository) {
        this.systemRepository = systemRepository;
        this.entityRepository = entityRepository;
        this.jsonRepository = jsonRepository;
        this.jsonPropertyRepository = jsonPropertyRepository;
        this.restRepository = restRepository;
        this.erdRepository = erdRepository;
    }

    @Override
    public InputStream generateHtml(int systemId) throws Exception {

        System system = systemRepository.get(systemId);

        HtmlDocumentation html = new HtmlDocumentation(system.getName());
        html.index(system);

        List<Json> jsons = jsonRepository.list(system.getId());

        html.listJson(jsons);

        for (Json j : jsons) {
            List<JsonProperty> jsonProperties = jsonPropertyRepository.list(j.getId());
            html.json(j, jsonProperties);
        }

        List<Entities> entities = entityRepository.list(system.getId());
        html.listRest(entities);
        html.listErd(entities);

        for (Entities e : entities) {
            List<Rest> rests = restRepository.list(e.getId());

            List<Erd> listErd = erdRepository.list(e.getId());
            if (listErd != null) {
                if (listErd.size() > 0) {
                    Erd erd = listErd.get(0);
                    html.erd(e, erd);
                }
            }

            html.rest(e, rests);
        }

        Zip zip = new Zip();
        DocumentFile doc = new DocumentFile();
        zip.generate(doc.getAddressDocumentation() + system.getName(), doc.getAddressDocumentation() + system.getName() + ".zip");
        return doc.download(doc.getAddressDocumentation() + system.getName() + ".zip");

    }

}
