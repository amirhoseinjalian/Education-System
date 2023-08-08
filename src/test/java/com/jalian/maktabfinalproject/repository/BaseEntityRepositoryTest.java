package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.BaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public abstract class BaseEntityRepositoryTest<Id, Value extends BaseEntity<Id>, Repository extends BaseRepository<Value, Id>> {

    protected Value value;

    protected abstract Repository repository();

    @BeforeEach
    //must be annotated @BeforeEach in the sub classes!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    protected abstract void setup();

    protected abstract Value newInstance();

    @Test
    protected void save() {
        Value savedValue = repository().save(value);
        assertThat(savedValue).isNotNull();
        assertThat(savedValue).isEqualTo(value);
    }

    @Test
    protected void findById() {
        repository().save(value);
        Value founded = repository().findById(value.getId()).get();
        assertThat(founded).isNotNull();
    }

    @Test
    protected void update() {
        repository().save(value);
        Value saved = repository().findById(value.getId()).get();
        saved = newInstance();
        Value updated = repository().save(saved);
        //need validation
    }

    @Test
    void delete() {
        repository().save(value);
        repository().deleteById(value.getId());
        Optional<Value> deleted = repository().findById(value.getId());
        assertThat(deleted).isEmpty();
    }

    @Test
    void findAll() {
        Value value1 = newInstance();
        repository().save(value);
        repository().save(value1);
        List<Value> values = repository().findAll();
        assertThat(values).isNotNull();
        assertThat(values.size()).isEqualTo(2);
    }
}
