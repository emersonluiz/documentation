package br.com.emersonluiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.emersonluiz.model.JsonProperty;
import br.com.emersonluiz.service.JsonPropertyService;

@RestController
@RequestMapping("/json/{jsonId}/property")
public class JsonPropertyController extends ExceptionController {

    private JsonPropertyService jsonPropertyService;

    @Autowired
    public JsonPropertyController(JsonPropertyService jsonPropertyService) {
        this.jsonPropertyService = jsonPropertyService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public JsonProperty create(@PathVariable("jsonId") int jsonId, @RequestBody JsonProperty jsonProperty) throws Exception {
        jsonPropertyService.create(jsonId, jsonProperty);
        return jsonProperty;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    public JsonProperty get(@PathVariable("jsonId") int jsonId, @PathVariable("id") int id) throws Exception {
        return jsonPropertyService.get(jsonId, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("jsonId") int jsonId, @PathVariable("id") int id) throws Exception {
        jsonPropertyService.delete(jsonId, id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
    public List<JsonProperty> list(@PathVariable("jsonId") int jsonId) throws Exception {
        return jsonPropertyService.list(jsonId);
    }
}
