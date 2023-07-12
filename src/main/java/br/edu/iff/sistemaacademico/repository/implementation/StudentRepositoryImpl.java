package br.edu.iff.sistemaacademico.repository.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<String, Student> db = new HashMap<>();

    @Override
    public Student findById(String id) {
        return db.get(id);
    }

    @Override
    public Collection<Student> findAll() {
        return db.values();
    }

    @Override
    public Student insert(Student data) throws IllegalArgumentException {
        if (db.containsKey(data.getId())){
            throw new IllegalArgumentException("Dado já existe");
        }

        db.put(data.getId(), data);

        return db.get(data.getId());
    }

    @Override
    public Student update(String id, Student data) throws IllegalArgumentException {
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
