package br.edu.iff.sistemaacademico.repository.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SubjectRepositoryImpl implements SubjectRepository {
    private final Map<String, Subject> db = new HashMap<>();

    @Override
    public Subject findById(String id) {
        return db.get(id);
    }

    @Override
    public Collection<Subject> findAll() {
        return db.values();
    }

    @Override
    public Subject insert(Subject data) throws IllegalArgumentException {
        if (db.containsKey(data.getId())){
            throw new IllegalArgumentException("Dado já existe");
        }

        db.put(data.getId(), data);

        return db.get(data.getId());
    }

    @Override
    public Subject update(String id, Subject data) throws IllegalArgumentException {
        if (!db.containsKey(id)){
            throw new IllegalArgumentException("Dado não encontrado");
        }

        db.replace(data.getId(), data);

        return db.get(data.getId());
    }

    @Override
    public void delete(String id) throws IllegalArgumentException {
        if (!db.containsKey(id)){
            throw new IllegalArgumentException("Dado não encontrado");
        }

        db.remove(id);
    }
}
