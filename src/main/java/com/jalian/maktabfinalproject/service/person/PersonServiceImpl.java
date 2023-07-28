package com.jalian.maktabfinalproject.service.person;

import com.jalian.maktabfinalproject.entity.Person;
import com.jalian.maktabfinalproject.repository.PersonRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class PersonServiceImpl<Value extends Person, Repository extends PersonRepository<Value>>
        extends BaseServiceImpl<Value, String, Repository>
        implements PersonService<Value> {

    public PersonServiceImpl(Repository repository) {
        super(repository);
    }

    @Override
    public List<Person> getAllWaitingPersons() {
        return getRepository().getAllWaitingPersons();
    }

    @Override
    public void confirmUsersReg(String username) {
        getRepository().confirmUsersReg(username);
    }
}
