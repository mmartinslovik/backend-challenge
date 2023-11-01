package com.example.taskmanager.service.common;

import com.example.taskmanager.data.model.BaseEntity;
import com.example.taskmanager.data.repository.BaseRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseServiceImpl<E extends BaseEntity, K> implements BaseService<E, K> {

    private final BaseRepository<E, K> repository;
    private final Validator validator;

    @Autowired
    protected BaseServiceImpl(BaseRepository<E, K> repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    @Transactional
    public E save(E entity) {
        var violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return repository.save(entity);
    }

    @Override
    @Transactional
    public Optional<E> findById(K id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public List<E> findAll() {
        return (List<E>) repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(K id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean existsById(K id) {
        return repository.existsById(id);
    }
}
