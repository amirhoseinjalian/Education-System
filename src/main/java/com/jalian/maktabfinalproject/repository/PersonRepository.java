package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean // age ino bardarim, baraye query haye generic hamaro person mibine va mirine tu karemun, masalan tu
//listi az student, teacher ham mizare
@EnableJpaRepositories(considerNestedRepositories = true)
public interface PersonRepository<Value extends Person> extends BaseRepository<Value, String> {

    @Query("select p from Person p where p.status = 'WAITING'")
    public List<Person> getAllWaitingPersons();

    @Query("update Person p set p.status = 'CONFIRMED' where p.id = :username")
    @Modifying
    public void confirmUsersReg(@Param("username") String username);

    //yek mesal entezaie az query haye generic
    @Query("select v from #{#entityName} v")
    List<Value> findByLastName();
}
