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

import br.com.emersonluiz.model.Rest;
import br.com.emersonluiz.service.RestService;

@RestController
@RequestMapping("/entity/{entityId}/rest")
public class RestsController extends ExceptionController {

    private RestService restService;

    @Autowired
    public RestsController(RestService restService) {
        this.restService = restService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public Rest create(@PathVariable("entityId") int entityId, @RequestBody Rest rest) throws Exception {
        restService.create(entityId, rest);
        return rest;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    public Rest get(@PathVariable("entityId") int entityId, @PathVariable("id") int id) throws Exception {
        return restService.get(entityId, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("entityId") int entityId, @PathVariable("id") int id) throws Exception {
        restService.delete(entityId, id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
    public List<Rest> list(@PathVariable("entityId") int entityId) throws Exception {
        return restService.list(entityId);
    }
}
