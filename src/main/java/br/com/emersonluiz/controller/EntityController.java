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

import br.com.emersonluiz.model.Entities;
import br.com.emersonluiz.service.EntityService;

@RestController
@RequestMapping("/system/{systemId}/entity")
public class EntityController extends ExceptionController {

    private EntityService entityService;

    @Autowired
    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public Entities create(@PathVariable("systemId") int systemId, @RequestBody Entities entities) throws Exception {
        entityService.create(systemId, entities);
        return entities;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    public Entities get(@PathVariable("systemId") int systemId, @PathVariable("id") int id) throws Exception {
        return entityService.get(systemId, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("systemId") int systemId, @PathVariable("id") int id) throws Exception {
        entityService.delete(systemId, id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
    public List<Entities> list(@PathVariable("systemId") int systemId) throws Exception {
        return entityService.list(systemId);
    }
}
