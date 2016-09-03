package br.com.emersonluiz.model;

import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="erd")
public class Erd {

    @Id
    @GeneratedValue
    private int id;

    private String fileName;

    @Column(name="content_type")
    private String contentyType;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private Entities entities;

    @JsonIgnore
    @Transient
    private InputStream inputStream;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentyType() {
        return contentyType;
    }

    public void setContentyType(String contentyType) {
        this.contentyType = contentyType;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
