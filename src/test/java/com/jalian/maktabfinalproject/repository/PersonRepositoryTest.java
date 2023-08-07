package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Person;
import com.jalian.maktabfinalproject.entity.RegistrationStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class PersonRepositoryTest<Value extends Person, Repository extends PersonRepository<Value>> extends
        BaseEntityRepositoryTest<String, Value, Repository> {

    @Test
    protected void getAllWaitingPersonsTest() {
        value = setup();
        Value value1 = newInstance();
        value1.setStatus(RegistrationStatus.CONFIRMED);
        repository().save(value1);
        value = repository().save(value);
        List<Person> waiting = repository().getAllWaitingPersons();
        assertThat(waiting).isNotNull();
        assertThat(waiting.size()).isEqualTo(1);
        assertThat(waiting.get(0)).isEqualTo(value);
    }

    @Test
    protected void confirmUsersRegTest() {
        value = setup();
        repository().save(value);
        repository().confirmUsersReg(value.getId());
        List<Person> people = repository().getAllWaitingPersons();
        assertThat(people.size()).isEqualTo(0);
    }
}
