package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Role;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends BaseRepository<Role, Long> {
}
