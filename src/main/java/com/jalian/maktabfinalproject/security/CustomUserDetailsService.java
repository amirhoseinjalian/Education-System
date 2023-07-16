/*
package com.jalian.maktabfinalproject.security;

import com.jalian.maktabfinalproject.entity.Person;
import com.jalian.maktabfinalproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier(value = "personRepository")
    private PersonRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = repository.findById(username);
        if(!person.isPresent()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(person.get());
    }
}
*/
