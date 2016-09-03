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

import br.com.emersonluiz.service.SystemService;
import br.com.emersonluiz.model.System;

@RestController
@RequestMapping("/system")
public class SystemController extends ExceptionController {

    private SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public System create(@RequestBody System system) {
        systemService.create(system);
        return system;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    public System get(@PathVariable("id") int id) throws Exception {
        return systemService.get(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) throws Exception {
        systemService.delete(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
    public List<System> list() {
        return systemService.list();
    }

}
