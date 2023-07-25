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
    public Professor findById(String username) {
        return db.get(username);
    }

    @Override
    public Collection<Professor> findAll() {
        return db.values();
    }

    @Override
    public Professor insert(Professor data) throws IllegalArgumentException {
        if (db.containsKey(data.getUsername())){
            throw new IllegalArgumentException("Dado já existe");
        }

        db.put(data.getUsername(), data);

        return db.get(data.getUsername());
    }

    @Override
    public Professor update(String id, Professor data) throws IllegalArgumentException {
        if (!db.containsKey(id)){
            throw new IllegalArgumentException("Dado não encontrado");
        }

        db.replace(data.getUsername(), data);

        return db.get(data.getUsername());
    }

    @Override
    public void delete(String id) throws IllegalArgumentException {
        if (!db.containsKey(id)){
            throw new IllegalArgumentException("Dado não encontrado");
        }

        db.remove(id);
    }
}
