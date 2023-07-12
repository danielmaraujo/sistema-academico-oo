package br.edu.iff.sistemaacademico.repository.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.repository.CourseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    private final Map<String, Course> db = new HashMap<>();

    @Override
    public Course findById(String id) {
        return db.get(id);
    }

    @Override
    public Collection<Course> findAll() {
        return db.values();
    }

    @Override
    public Course insert(Course data) throws IllegalArgumentException {
        if (db.containsKey(data.getId())){
            throw new IllegalArgumentException("Dado já existe");
        }

        db.put(data.getId(), data);

        return db.get(data.getId());
    }

    @Override
    public Course update(String id, Course data) throws IllegalArgumentException {
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
