package br.edu.iff.sistemaacademico.repository.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Professor;
import br.edu.iff.sistemaacademico.repository.ProfessorRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepository {
    private final Map<String, Professor> db = new HashMap<>();

    @Override
    public Professor findById(String id) {
        return db.get(id);
    }

    @Override
    public Collection<Professor> findAll() {
        return db.values();
    }

    @Override
    public Professor insert(Professor data) throws IllegalArgumentException {
        if (db.containsKey(data.getId())){
            throw new IllegalArgumentException("Dado já existe");
        }

        db.put(data.getId(), data);

        return db.get(data.getId());
    }

    @Override
    public Professor update(String id, Professor data) throws IllegalArgumentException {
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
