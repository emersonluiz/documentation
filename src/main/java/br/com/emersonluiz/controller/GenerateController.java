package br.com.emersonluiz.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.emersonluiz.service.GenerateService;

@RestController
@RequestMapping("/system/{systemId}/generate_documentation")
public class GenerateController extends ExceptionController {

    private GenerateService generateService;

    @Autowired
    public GenerateController(GenerateService generateService) {
        this.generateService = generateService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = { "multipart/form-data" })
    public ResponseEntity<InputStreamResource> get(@PathVariable("systemId") int systemId) throws Exception {
        InputStream zip = generateService.generateHtml(systemId);

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(new MediaType("application", "zip"));
        respHeaders.setContentDispositionFormData("attachment", "documentation.zip");
        InputStreamResource isr = new InputStreamResource(zip);
        return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
    }
}
