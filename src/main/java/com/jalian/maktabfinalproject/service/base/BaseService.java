package com.jalian.maktabfinalproject.service.base;

import com.jalian.maktabfinalproject.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<Value extends BaseEntity<Key>, Key> {

    Optional<Value> findById(Key key);

    List<Value> findAll();

    void deleteById(Key key);

    Value save(Value value);
}
