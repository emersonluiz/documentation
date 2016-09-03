package br.com.emersonluiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rest")
public class Rest {

    @Id
    @GeneratedValue
    private int id;

    private String action;

    private String description;

    private String method;

    private String resource;

    @ManyToOne(optional=true)
    @JoinColumn(name = "request_json_id", nullable=true)
    private Json requestJson; 

    @ManyToOne(optional=true)
    @JoinColumn(name = "response_json_id", nullable=true)
    private Json responseJson;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private Entities entities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Json getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(Json requestJson) {
        this.requestJson = requestJson;
    }

    public Json getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(Json responseJson) {
        this.responseJson = responseJson;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

}
