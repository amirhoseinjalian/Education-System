package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.QuizRepository;
import com.jalian.maktabfinalproject.service.answer.testAnswer.TestAnswerService;
import com.jalian.maktabfinalproject.service.quiz.QuizService;
import com.jalian.maktabfinalproject.service.quiz.QuizServiceImpl;
import com.jalian.maktabfinalproject.service.quizQuestion.QuizQuestionService;
import com.jalian.maktabfinalproject.service.studentQuiz.StudentQuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest extends BaseEntityServiceTest<Long, Quiz, QuizRepository, QuizService> {

    @Mock
    private QuizQuestionService quizQuestionService;

    @Mock
    private StudentQuizService studentQuizService;

    @Mock
    private TestAnswerService testAnswerService;

    @Override
    protected QuizService getService() {
        return new QuizServiceImpl(repository);
    }

    @Override
    protected Class<QuizRepository> getRepositoryClass() {
        return QuizRepository.class;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = Quiz.builder().
                id(1L).
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
    }

    @Override
    protected Quiz newInstance() {
        return Quiz.builder().
                id(2L).
                description("this is new quiz").
                date(new Date(System.currentTimeMillis())).
                time(22).
                title("new quiz").
                build();
    }

    @Override
    protected Quiz getNewValueForUpdate() {
        return Quiz.builder().
                id(1L).
                description("this is quiz 2").
                date(new Date(System.currentTimeMillis())).
                time(12).
                title("quiz 2").
                build();
    }

    @Test
    void addQuestionTest() {
        TestQuestion testQuestion = TestQuestion.builder()
                .id(3L)
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .title("test question title")
                .build();
        DescriptiveQuestion descriptiveQuestion = DescriptiveQuestion.builder()
                .id(4L)
                .question("descriptive question")
                .description("descriptive question description")
                .title("descriptive question title")
                .build();
        willDoNothing().given(quizQuestionService).addQuestion(value, testQuestion, 1.);
        willDoNothing().given(quizQuestionService).addQuestion(value, descriptiveQuestion, 15.5);
        service.addQuestion(value, testQuestion, 1.0);
        service.addQuestion(value, descriptiveQuestion, 15.5);
        verify(quizQuestionService, times(1)).addQuestion(value, testQuestion, 1.);
        verify(quizQuestionService, times(1)).addQuestion(value, descriptiveQuestion, 15.5);
    }

    @Test
    void addToCourseTest() {
        Student student = new Student();
        Course course = Course.builder()
                .id(3L)
                .title("java and lambdas")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .students(new ArrayList<>())
                .quizzes(new ArrayList<>())
                .build();
        given(repository.save(value)).willReturn(value);
        willDoNothing().given(studentQuizService).addStudent(value, student);
        service.addToCourse(course, value);
        List<Quiz> quizzes = course.getQuizzes();
        assertThat(quizzes.contains(value)).isTrue();
    }

    @Test
    void getAllowedQuizzesTest() {
        Student student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        StudentQuiz studentQuiz = StudentQuiz.builder()
                .quiz(value)
                .student(student)
                .grade(10.)
                .id(new StudentQuizKey(student.getId(), value.getId()))
                .build();
        value.setStudents(List.of(studentQuiz));
        StudentQuiz studentQuiz2 = StudentQuiz.builder()
                .quiz(newInstance())
                .student(student)
                .grade(10.)
                .id(new StudentQuizKey(student.getId(), value.getId()))
                .build();
        newInstance().setStudents(List.of(studentQuiz2));
        student.setQuizzes(List.of(studentQuiz, studentQuiz2));
        given(repository.getAllowedQuizzes(student.getId())).willReturn(List.of(value, newInstance()));
        List<Quiz> quizzes = service.getAllowedQuizzes(student);
        assertThat(quizzes).isEqualTo(List.of(value, newInstance()));
    }

    @Test
    void correctTestQuestionTest() throws Exception {
        Student student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        TestAnswer testAnswer1 = TestAnswer.builder().id(3L).correctOption(new Option("correct option")).build();
        TestAnswer testAnswer2 = TestAnswer.builder().id(4L).correctOption(new Option("correct option")).build();
        TestAnswer testAnswer3 = TestAnswer.builder().id(5L).correctOption(new Option("correct option")).build();
        TestQuestion testQuestion1 = TestQuestion.builder()
                .id(6L)
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .answer(testAnswer1)
                .title("test question title")
                .build();
        testAnswer1.setQuestion(testQuestion1);
        TestQuestion testQuestion2 = TestQuestion.builder()
                .id(7L)
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .answer(testAnswer2)
                .title("test question title")
                .build();
        testAnswer2.setQuestion(testQuestion2);
        TestQuestion testQuestion3 = TestQuestion.builder()
                .id(8L)
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .answer(testAnswer3)
                .title("test question title")
                .build();
        testAnswer3.setQuestion(testQuestion3);
        QuizQuestionJoinTable quizQuestionJoinTable1 = QuizQuestionJoinTable.builder()
                .quiz(value)
                .question(testQuestion1)
                .score(0.5)
                .id(new QuizQuestionKey(value.getId(), testQuestion1.getId()))
                .build();
        QuizQuestionJoinTable quizQuestionJoinTable2 = QuizQuestionJoinTable.builder()
                .quiz(value)
                .question(testQuestion2)
                .score(0.5)
                .id(new QuizQuestionKey(value.getId(), testQuestion2.getId()))
                .build();
        QuizQuestionJoinTable quizQuestionJoinTable3 = QuizQuestionJoinTable.builder()
                .quiz(value)
                .question(testQuestion3)
                .score(0.5)
                .id(new QuizQuestionKey(value.getId(), testQuestion3.getId()))
                .build();
        value.setQuestions(List.of(quizQuestionJoinTable1, quizQuestionJoinTable2, quizQuestionJoinTable3));
        StudentQuiz studentQuiz = StudentQuiz.builder()
                .quiz(value)
                .student(student)
                .isJoined(true)
                .id(new StudentQuizKey(student.getId(), value.getId()))
                .answers(List.of(testAnswer1, TestAnswer.builder().id(9L).build(), testAnswer3))
                .build();
        student.setQuizzes(List.of(studentQuiz));
        value.setStudents(List.of(studentQuiz));
        given(studentQuizService.findById(studentQuiz.getId())).willReturn(Optional.of(studentQuiz));
        given(testAnswerService.getAnswers(student, value)).willReturn(List.of(testAnswer1, TestAnswer.builder().id(9L).build(), testAnswer3));
        given(quizQuestionService.getQuestions(value)).willReturn(List.of(testQuestion1, testQuestion2, testQuestion3));
        given(quizQuestionService.findById(new QuizQuestionKey(value.getId(), testAnswer1.getQuestion().getId()))).willReturn(Optional.of(quizQuestionJoinTable1));
        given(quizQuestionService.findById(new QuizQuestionKey(value.getId(), testAnswer2.getQuestion().getId()))).willReturn(Optional.of(quizQuestionJoinTable2));
        given(quizQuestionService.findById(new QuizQuestionKey(value.getId(), testAnswer3.getQuestion().getId()))).willReturn(Optional.of(quizQuestionJoinTable3));
        given(studentQuizService.save(studentQuiz)).willReturn(studentQuiz);
        Map<String, Double> map = service.correctTestQuestion(student, value);
        assertThat(map.get(student.getId())).isEqualTo(1.);
    }
}
