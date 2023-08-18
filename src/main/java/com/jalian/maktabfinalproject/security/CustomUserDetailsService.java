package com.jalian.maktabfinalproject.security;

import com.jalian.maktabfinalproject.entity.Person;
import com.jalian.maktabfinalproject.repository.AdminRepository;
import com.jalian.maktabfinalproject.repository.StudentRepository;
import com.jalian.maktabfinalproject.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<? extends Person> person = studentRepository.findById(username);
        if (person.isPresent()) {
            return new CustomUserDetails(person.get());
        }
        person = teacherRepository.findById(username);
        if (person.isPresent()) {
            return new CustomUserDetails(person.get());
        }
        person = adminRepository.findById(username);
        if (person.isPresent()) {
            return new CustomUserDetails(person.get());
        }
        throw new UsernameNotFoundException("not found");
    }
}
