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
    protected abstract Value setup();

    protected abstract Value newInstance();

    @Test
    protected void save() {
        value = setup();
        Value savedValue = repository().save(value);
        assertThat(savedValue).isNotNull();
        assertThat(savedValue).isEqualTo(value);
    }

    @Test
    protected void findById() {
        value = setup();
        repository().save(value);
        Value founded = repository().findById(value.getId()).get();
        assertThat(founded).isNotNull();
    }

    @Test
    protected void update() {
        value = setup();
        repository().save(value);
        Value saved = repository().findById(value.getId()).get();
        saved = newInstance();
        Value updated = repository().save(saved);
        //need validation
    }

    @Test
    void delete() {
        value = setup();
        repository().save(value);
        repository().deleteById(value.getId());
        Optional<Value> deleted = repository().findById(value.getId());
        assertThat(deleted).isEmpty();
    }

    @Test
    void findAll() {
        value = setup();
        Value value1 = newInstance();
        repository().save(value);
        repository().save(value1);
        List<Value> values = repository().findAll();
        assertThat(values).isNotNull();
        assertThat(values.size()).isEqualTo(2);
    }
}
