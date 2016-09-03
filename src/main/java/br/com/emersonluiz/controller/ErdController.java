package br.com.emersonluiz.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.emersonluiz.model.Erd;
import br.com.emersonluiz.service.ErdService;

@RestController
@RequestMapping("/entity/{entityId}/erd")
public class ErdController extends ExceptionController {

    private ErdService erdService;

    @Autowired
    public ErdController(ErdService erdService) {
        this.erdService = erdService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" }, produces = { "application/json" })
    public Erd create(@PathVariable("entityId") int entityId, @RequestParam("file") MultipartFile file) throws Exception {
        Erd erd = new Erd();
        if (!file.isEmpty()) {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String contentyType = file.getContentType();
            erd.setFileName(fileName);
            erd.setContentyType(contentyType);
            erdService.create(entityId, erd, inputStream);
        }
        return erd;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "multipart/form-data" })
    public ResponseEntity<InputStreamResource> get(@PathVariable("entityId") int entityId, @PathVariable("id") int id) {
        try {
            Erd erd = erdService.get(entityId, id);

            HttpHeaders respHeaders = new HttpHeaders();
            String[] content = erd.getContentyType().split("/");

            respHeaders.setContentType(new MediaType(content[0], content[1]));
            respHeaders.setContentDispositionFormData("attachment", erd.getFileName());

            InputStreamResource isr = new InputStreamResource(erd.getInputStream());
            return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public void delete(@PathVariable("entityId") int entityId, @PathVariable("id") int id) throws Exception {
        erdService.delete(entityId, id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
    public List<Erd> list(@PathVariable("entityId") int entityId) {
        return erdService.list(entityId);
    }

}
