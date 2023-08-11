package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.BaseEntity;
import com.jalian.maktabfinalproject.repository.*;
import com.jalian.maktabfinalproject.service.base.BaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
//needed for update test????????????????????????????????????????????????????????
public abstract class BaseEntityServiceTest<Id, Value extends BaseEntity<Id>, Repository extends BaseRepository<Value, Id>, Service extends BaseService<Value, Id>> {

    @Mock(extraInterfaces = {StudentRepository.class, TeacherRepository.class, CourseRepository.class, AnswerRepository.class
            , QuestionRepository.class})
    //(extraInterfaces = {StudentRepository.class, PersonRepository.class}) needed for class casting!!!!!!!!!!!!!!!!!!!!!!!!!!!
    protected Repository repository;

    @InjectMocks
    protected Service service = getService();

    protected Value value;

    protected abstract Service getService();

    protected abstract void setup();

    protected abstract Value newInstance();

    protected abstract Value getNewValueForUpdate();

    @BeforeEach
    void validateId() {
        setup();
        if (value.getId() == null) {
            throw new RuntimeException("Id is required");
        }
    }

    @Test
    void save() {
        given(repository.save(value)).willReturn(value);
        Value savedEntity = service.save(value);
        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity).isEqualTo(value);
    }

    @Test
    void findByIdTest() {
        given(repository.findById(value.getId())).willReturn(Optional.of(value));
        Value foundedEntity = service.findById(value.getId()).get();
        assertThat(foundedEntity).isNotNull();
        assertThat(foundedEntity).isEqualTo(value);
    }

    @Test
    void findAllTest() {
        given(repository.findAll()).willReturn(List.of(value, newInstance()));
        List<Value> values = service.findAll();
        assertThat(values).isEqualTo(List.of(value, newInstance()));
    }

    @Test
    void deleteTest() {
        willDoNothing().given(repository).deleteById(value.getId());
        service.deleteById(value.getId());
        verify(repository, times(1)).deleteById(value.getId());
    }

    @Test
    void updateTest() {
        given(repository.save(value)).willReturn(value);
        value = getNewValueForUpdate();
        given(repository.save(value)).willReturn(getNewValueForUpdate());
        assertThat(value).isEqualTo(getNewValueForUpdate());
    }
}
