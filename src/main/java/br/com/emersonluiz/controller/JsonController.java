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

import br.com.emersonluiz.model.Json;
import br.com.emersonluiz.service.JsonService;

@RestController
@RequestMapping("/system/{systemId}/json")
public class JsonController extends ExceptionController {

    private JsonService jsonService;

    @Autowired
    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public Json create(@PathVariable("systemId") int systemId, @RequestBody Json json) throws Exception {
        jsonService.create(systemId, json);
        return json;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    public Json get(@PathVariable("systemId") int systemId, @PathVariable("id") int id) throws Exception {
        return jsonService.get(systemId, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("systemId") int systemId, @PathVariable("id") int id) throws Exception {
        jsonService.delete(systemId, id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
    public List<Json> list(@PathVariable("systemId") Integer systemId) throws Exception {
        return jsonService.list(systemId);
    }
}
