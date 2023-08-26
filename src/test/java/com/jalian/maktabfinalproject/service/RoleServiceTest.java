package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.Role;
import com.jalian.maktabfinalproject.entity.RoleNames;
import com.jalian.maktabfinalproject.repository.RoleRepository;
import com.jalian.maktabfinalproject.service.role.RoleService;
import com.jalian.maktabfinalproject.service.role.RoleServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest extends BaseEntityServiceTest<Long, Role, RoleRepository, RoleService> {

    @Override
    protected RoleService getService() {
        return new RoleServiceImpl(repository);
    }

    @Override
    protected Class<RoleRepository> getRepositoryClass() {
        return RoleRepository.class;
    }

    @Override
    protected void setup() {
        value = Role.builder().
                id(1L).
                name(RoleNames.STUDENT).build();
    }

    @Override
    protected Role newInstance() {
        return Role.builder().
                id(2L).
                name(RoleNames.TEACHER).build();
    }

    @Override
    protected Role getNewValueForUpdate() {
        return Role.builder().
                id(1L).
                name(RoleNames.TEACHER).build();
    }
}
