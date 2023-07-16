package com.jalian.maktabfinalproject.service.person;

import com.jalian.maktabfinalproject.entity.Person;
import com.jalian.maktabfinalproject.service.base.BaseService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonService<Value extends Person> extends BaseService<Value, String> {

    List<Person> getAllWaitingPersons();

    void confirmUsersReg(String username);

    List<Value> findByLastName();
}
