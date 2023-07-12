package br.edu.iff.sistemaacademico.repository;

import java.util.Collection;

public interface Repository<T> {
    T findById(String id);
    Collection<T> findAll();
    T insert(T data) throws IllegalArgumentException;
    T update(String id, T data) throws IllegalArgumentException;
    void delete(String id) throws IllegalArgumentException;
}
