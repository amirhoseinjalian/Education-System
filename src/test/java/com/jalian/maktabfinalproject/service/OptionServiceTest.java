package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.Option;
import com.jalian.maktabfinalproject.repository.OptionRepository;
import com.jalian.maktabfinalproject.service.option.OptionService;
import com.jalian.maktabfinalproject.service.option.OptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OptionServiceTest extends BaseEntityServiceTest<Long, Option, OptionRepository, OptionService> {

    @Override
    @BeforeEach
    protected void setup() {
        value = Option.builder().id(1L).expression("option 1").build();
    }

    @Override
    protected Option newInstance() {
        return Option.builder().id(2L).expression("option 2").build();
    }

    @Override
    protected OptionService getService() {
        return new OptionServiceImpl(super.repository);
    }

    @Override
    protected Option getNewValueForUpdate() {
        return Option.builder().id(1L).expression("new option").build();
    }
}
