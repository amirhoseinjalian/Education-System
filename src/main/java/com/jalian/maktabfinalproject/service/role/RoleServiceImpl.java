package com.jalian.maktabfinalproject.service.role;

import com.jalian.maktabfinalproject.entity.Role;
import com.jalian.maktabfinalproject.repository.RoleRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role, Long, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
    }
}
