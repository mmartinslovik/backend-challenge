package com.example.taskmanager.service.common;

import java.util.List;
import java.util.Optional;

/**
 * BaseService for common CRUD operations
 *
 * @param <E> Entity
 * @param <K> Key
 */
public interface BaseService<E, K> {

    E save(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

    void deleteById(K id);

    boolean existsById(K id);
}
