package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Role;
import com.jalian.maktabfinalproject.entity.RoleNames;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
public class RoleRepositoryTest extends BaseEntityRepositoryTest<Long, Role, RoleRepository> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected RoleRepository repository() {
        return roleRepository;
    }

    @Override
    @BeforeEach//needed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    protected void setup() {
        value = Role.builder().
                name(RoleNames.STUDENT).build();
    }

    @Override
    protected Role newInstance() {
        return Role.builder().
                name(RoleNames.TEACHER).build();
    }
}
