package com.jalian.maktabfinalproject.service.option;

import com.jalian.maktabfinalproject.entity.Option;
import com.jalian.maktabfinalproject.repository.OptionRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OptionServiceImpl extends BaseServiceImpl<Option, Long, OptionRepository> implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public OptionServiceImpl(OptionRepository repository) {
        super(repository);
    }
}
