package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Option;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OptionRepositoryTest extends BaseEntityRepositoryTest<Long, Option, OptionRepository> {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    protected OptionRepository repository() {
        return optionRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = Option.builder().expression("option 1").build();
    }

    @Override
    protected Option newInstance() {
        return Option.builder().expression("option 2").build();
    }
}
