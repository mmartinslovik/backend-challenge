package com.example.taskmanager.data.repository;

import com.example.taskmanager.data.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * BaseRepository for common CRUD operations
 *
 * @param <E> Entity
 * @param <K> Key
 */
@Repository
public interface BaseRepository<E extends BaseEntity, K> extends CrudRepository<E, K> {

    <S extends E> S save(S entity);

    Optional<E> findById(K id);

    boolean existsById(K id);

    Iterable<E> findAll();

    void deleteById(K id);
}
