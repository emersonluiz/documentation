package br.com.emersonluiz.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonluiz.model.Erd;
import br.com.emersonluiz.model.Entities;
import br.com.emersonluiz.repository.ErdRepository;
import br.com.emersonluiz.repository.EntityRepository;
import br.com.emersonluiz.util.DocumentFile;

@Service
public class DefaultErdService implements ErdService {

    private ErdRepository erdRepository;
    private EntityRepository entityRepository;

    @Autowired
    public DefaultErdService(ErdRepository erdRepository, EntityRepository entityRepository) {
        this.erdRepository = erdRepository;
        this.entityRepository = entityRepository;
    }

    @Override
    public Erd create(int entityId, Erd erd, InputStream inputStream) throws Exception {
        Entities entities = entityRepository.get(entityId);
        erd.setEntities(entities);
        erdRepository.create(erd);
        DocumentFile df = new DocumentFile();
        df.upload(inputStream, "system/"+erd.getEntities().getSystem().getId()+"/entity/"+erd.getEntities().getId()+"/erd/"+erd.getId(), erd.getFileName());
        return erd;
    }

    @Override
    public Erd get(int entityId, int id) throws Exception {
        entityRepository.get(entityId);
        Erd erd = erdRepository.get(id);
        DocumentFile df = new DocumentFile();
        InputStream inputStream = df.download("system/"+erd.getEntities().getSystem().getId()+"/entity/"+erd.getEntities().getId()+"/erd/"+erd.getId(), erd.getFileName());
        erd.setInputStream(inputStream);
        return erd;
    }

    @Override
    public void delete(int entityId, int id) throws Exception {
        entityRepository.get(entityId);
        Erd erd = erdRepository.get(id);

        DocumentFile df = new DocumentFile();
        df.delete("system/"+erd.getEntities().getSystem().getId()+"/entity/"+erd.getEntities().getId()+"/erd/"+erd.getId(), erd.getFileName());

        erdRepository.delete(id);
    }

    @Override
    public List<Erd> list(int entityId) {
        try {
            entityRepository.get(entityId);
            List<Erd> list = erdRepository.list(entityId);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
