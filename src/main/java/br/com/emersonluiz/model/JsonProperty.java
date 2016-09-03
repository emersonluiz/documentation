package br.com.emersonluiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="json_properties")
public class JsonProperty {

    @Id
    @GeneratedValue
    private int id;

    private String property;

    private String type;

    @ManyToOne
    @JoinColumn(name="parent_json_id")
    private Json parentJson;

    @ManyToOne(optional=true)
    @JoinColumn(name="related_json_id", nullable=true)
    private Json relatedJson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Json getParentJson() {
        return parentJson;
    }

    public void setParentJson(Json parentJson) {
        this.parentJson = parentJson;
    }

    public Json getRelatedJson() {
        return relatedJson;
    }

    public void setRelatedJson(Json relatedJson) {
        this.relatedJson = relatedJson;
    }

}
