package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.Person;
import com.jalian.maktabfinalproject.repository.PersonRepository;
import com.jalian.maktabfinalproject.service.person.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public abstract class PersonServiceTest<Value extends Person, Repository extends PersonRepository<Value>, Service extends PersonService<Value>>
        extends BaseEntityServiceTest<String, Value, Repository, Service> {

    @Test
    void getAllWaitingPersonsTest() {
        given(repository.getAllWaitingPersons()).willReturn(List.of(value, newInstance()));
        List<Person> waitingUsers = service.getAllWaitingPersons();
        assertThat(waitingUsers).isEqualTo(List.of(value, newInstance()));
    }

    @Test
    void confirmUsersRegTest() {
        willDoNothing().given(repository).confirmUsersReg(value.getId());
        given((repository).getAllWaitingPersons()).willReturn(List.of(newInstance()));
        service.confirmUsersReg(value.getId());
        List<Person> people = service.getAllWaitingPersons();
        assertThat(people.contains(value)).isFalse();
        verify(repository, times(1)).confirmUsersReg(value.getId());
    }
}
