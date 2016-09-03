package br.com.emersonluiz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonluiz.exception.BadRequestException;
import br.com.emersonluiz.exception.NotFoundException;
import br.com.emersonluiz.model.Json;
import br.com.emersonluiz.model.JsonProperty;
import br.com.emersonluiz.repository.JsonPropertyRepository;
import br.com.emersonluiz.repository.JsonRepository;

@Service
public class DefaultJsonPropertyService implements JsonPropertyService {

    private JsonPropertyRepository jsonPropertyRepository;
    private JsonRepository jsonRepository;
    private static final Logger logger = LoggerFactory.getLogger(DefaultJsonPropertyService.class);

    @Autowired
    public DefaultJsonPropertyService(JsonPropertyRepository jsonPropertyRepository, JsonRepository jsonRepository) {
        this.jsonPropertyRepository = jsonPropertyRepository;
        this.jsonRepository = jsonRepository;
    }

    @Override
    public JsonProperty create(int jsonId, JsonProperty jsonProperty) throws Exception {
        Json parentJson = jsonRepository.get(jsonId);
        jsonProperty.setParentJson(parentJson);

        if (jsonProperty.getRelatedJson() != null) {
            Json relatedJson;
            try {
                relatedJson = jsonRepository.get(jsonProperty.getRelatedJson().getId());
                jsonProperty.setRelatedJson(relatedJson);
            } catch (NotFoundException e) {
                logger.error("Related JSON id: " + jsonProperty.getRelatedJson().getId() + " is invalid");
                throw new BadRequestException("Related JSON id: " + jsonProperty.getRelatedJson().getId() + " is invalid");
            }
        }

        jsonPropertyRepository.create(jsonProperty);
        return jsonProperty;
    }

    @Override
    public JsonProperty get(int jsonId, int id) throws Exception {
        jsonRepository.get(jsonId);
        JsonProperty jsonProperty = jsonPropertyRepository.get(id);
        return jsonProperty;
    }

    @Override
    public void delete(int jsonId, int id) throws Exception {
        jsonRepository.get(jsonId);
        jsonPropertyRepository.delete(id);
    }

    @Override
    public List<JsonProperty> list(int jsonId) throws Exception {
        jsonRepository.get(jsonId);
        List<JsonProperty> list = jsonPropertyRepository.list(jsonId);
        return list;
    }

}
