package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Transactional
public abstract class AnswerRepositoryTest<AnswerValue extends Answer, Repository extends AnswerRepository<AnswerValue>> extends BaseEntityRepositoryTest<Long, AnswerValue, Repository> {

    @Autowired
    protected StudentQuizRepository studentQuizRepository;

    @Autowired
    protected StudentRepository studentRepository;

    @Autowired
    protected QuizRepository quizRepository;

    @Test
    protected void getAllAnswersTest() {
        //given::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        List<Answer> answers = new ArrayList<>();
        Answer testAnswer = TestAnswer.builder().correctOption(new Option("correct option")).build();
        //why is DownCasting  needed????????????????????????????????????????????????????????????????????????
        testAnswer = (TestAnswer) ((AnswerRepository) repository()).save(testAnswer);
        DescriptiveAnswer descriptiveAnswer = DescriptiveAnswer.builder().answer("cirrect answer").build();
        //why is DownCasting  needed????????????????????????????????????????????????????????????????????????
        descriptiveAnswer = (DescriptiveAnswer) ((AnswerRepository) repository()).save(descriptiveAnswer);
        answers.add(testAnswer);
        answers.add(descriptiveAnswer);
        Student student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT)).
                build();
        //why saving student is needed here but not in TestAnswerRepositoryTest not??????????????????????????????????????????????
        student = studentRepository.save(student);
        Quiz quiz = Quiz.builder().
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
        StudentQuiz studentQuiz = StudentQuiz.builder().
                student(student).
                quiz(quiz).
                studentQuizKey(new StudentQuizKey(student.getId(), quiz.getId())).
                isJoined(false).
                answers(answers).
                build();
        student.setQuizzes(List.of(studentQuiz));
        //student = studentRepository.saveAndFlush(student);
        quiz = quizRepository.saveAndFlush(quiz);
        quiz.setStudents(List.of(studentQuiz));
        for (Answer answer : answers) {
            answer.setStudentQuiz(studentQuiz);
        }
        //when::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        List<Answer> savedAnswers = repository().getAllAnswers(student.getId(), quiz.getId());
        //then::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        assertThat(savedAnswers).isNotNull();
        assertThat(savedAnswers).isEqualTo(answers);
        assertThat(savedAnswers.size()).isEqualTo(2);
    }
}
