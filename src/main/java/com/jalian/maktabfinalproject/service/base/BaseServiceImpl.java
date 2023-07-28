package com.jalian.maktabfinalproject.service.base;

import com.jalian.maktabfinalproject.entity.BaseEntity;
import com.jalian.maktabfinalproject.repository.BaseRepository;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Getter
public abstract class BaseServiceImpl<Value extends BaseEntity<Key>, Key, Repository extends BaseRepository<Value, Key>>
        implements BaseService<Value, Key> {

    private Repository repository;

    public BaseServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Value> findById(Key key) {
        return repository.findById(key);
    }

    @Override
    public List<Value> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Key key) {
        repository.deleteById(key);
    }

    @Override
    public Value save(Value value) {
        return repository.save(value);
    }
}
