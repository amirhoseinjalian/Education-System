package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class QuizQuestionRepositoryTest extends BaseEntityRepositoryTest<QuizQuestionKey, QuizQuestionJoinTable, QuizQuestionRepository> {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private DescriptiveQuestionRepository descriptiveQuestionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    protected QuizQuestionRepository repository() {
        return quizQuestionRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        Quiz quiz = Quiz.builder().
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
        quiz = quizRepository.save(quiz);
        TestQuestion testQuestion = TestQuestion.builder()
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .title("test question title")
                .build();
        testQuestion = testQuestionRepository.save(testQuestion);
        value = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(testQuestion)
                .score(0.5)
                .id(new QuizQuestionKey(quiz.getId(), testQuestion.getId()))
                .build();
        //why caused Caused by: javax.persistence.EntityExistsException: A different object with the same identifier value was already associated with the session ????????????????????????????????
        /*quiz.setQuestions(List.of(value));
        testQuestion.setQuizzes(List.of(value));*/
    }

    @Override
    protected QuizQuestionJoinTable newInstance() {
        Quiz quiz = Quiz.builder().
                description("this is quiz 2").
                date(new Date(System.currentTimeMillis())).
                time(5).
                title("quiz 2").
                build();
        quiz = quizRepository.save(quiz);
        DescriptiveQuestion descriptiveQuestion = DescriptiveQuestion.builder()
                .question("descriptive question")
                .description("descriptive question description")
                .title("descriptive question title")
                .build();
        descriptiveQuestion = descriptiveQuestionRepository.save(descriptiveQuestion);
        return QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(descriptiveQuestion)
                .score(10.0)
                .id(new QuizQuestionKey(quiz.getId(), descriptiveQuestion.getId()))
                .build();
    }

    @Test
    void getQuizzes() {
        value = repository().save(value);
        Quiz quiz = Quiz.builder().
                description("this is quiz 3").
                date(new Date(System.currentTimeMillis())).
                time(5).
                title("quiz 3").
                build();
        quiz = quizRepository.save(quiz);
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(value.getQuestion())
                .score(0.5)
                .id(new QuizQuestionKey(quiz.getId(), value.getQuestion().getId()))
                .build();
        quiz.setQuestions(List.of(quizQuestionJoinTable));
        List<Quiz> quizzes = repository().getQuizzes(value.getQuestion().getId());
        assertThat(quizzes).isEqualTo(List.of(value.getQuiz(), quiz));
    }

    @Test
    void getQuestions() {
        value = repository().save(value);
        DescriptiveQuestion descriptiveQuestion = DescriptiveQuestion.builder()
                .question("descriptive question")
                .description("descriptive question description")
                .title("descriptive question title")
                .build();
        descriptiveQuestion = descriptiveQuestionRepository.save(descriptiveQuestion);
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable.builder()
                .quiz(value.getQuiz())
                .question(descriptiveQuestion)
                .score(0.5)
                .id(new QuizQuestionKey(value.getQuiz().getId(), descriptiveQuestion.getId()))
                .build();
        descriptiveQuestion.setQuizzes(List.of(quizQuestionJoinTable));
        List<Question> questions = repository().getQuestions(value.getQuiz().getId());
        assertThat(questions).isEqualTo(List.of(value.getQuestion(), descriptiveQuestion));
    }

    @Test
    void getScore() {
        value = repository().save(value);
        double score = repository().getScore(value.getQuiz().getId(), value.getQuestion().getId());
        assertThat(score).isEqualTo(0.5);
    }

    @Test
    void getTestQuestionsTest() {
        value = repository().save(value);
        QuizQuestionJoinTable quizQuestionJoinTable = newInstance();
        quizQuestionJoinTable = repository().save(quizQuestionJoinTable);
        Course course = Course.builder()
                .title("java")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .build();
        course = courseRepository.save(course);
        value.getQuiz().setCourse(course);
        quizQuestionJoinTable.getQuiz().setCourse(course);
        course.setQuizzes(List.of(value.getQuiz(), quizQuestionJoinTable.getQuiz()));
        DescriptiveQuestion descriptiveQuestion = DescriptiveQuestion.builder()
                .question("descriptive question")
                .description("descriptive question description")
                .title("descriptive question title")
                .build();
        descriptiveQuestion = descriptiveQuestionRepository.save(descriptiveQuestion);
        QuizQuestionJoinTable quizQuestionJoinTable2 = QuizQuestionJoinTable.builder()
                .quiz(value.getQuiz())
                .question(descriptiveQuestion)
                .score(0.5)
                .id(new QuizQuestionKey(value.getQuiz().getId(), descriptiveQuestion.getId()))
                .build();
        descriptiveQuestion.setQuizzes(List.of(quizQuestionJoinTable2));
        TestQuestion testQuestion = TestQuestion.builder()
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .title("test question title")
                .build();
        testQuestion = testQuestionRepository.save(testQuestion);
        QuizQuestionJoinTable quizQuestionJoinTable3 = QuizQuestionJoinTable.builder()
                .quiz(quizQuestionJoinTable.getQuiz())
                .question(testQuestion)
                .score(0.5)
                .id(new QuizQuestionKey(quizQuestionJoinTable.getQuiz().getId(), testQuestion.getId()))
                .build();
        testQuestion.setQuizzes(List.of(quizQuestionJoinTable3));
        List<TestQuestion> testQuestions = repository().getQuestions(course.getId(), TestQuestion.class);
        assertThat(testQuestions).isEqualTo(List.of(value.getQuestion(), testQuestion));
    }

    @Test
    void getDescriptiveQuestionsTest() {
        value = repository().save(value);
        QuizQuestionJoinTable quizQuestionJoinTable = newInstance();
        quizQuestionJoinTable = repository().save(quizQuestionJoinTable);
        Course course = Course.builder()
                .title("java")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .build();
        course = courseRepository.save(course);
        value.getQuiz().setCourse(course);
        quizQuestionJoinTable.getQuiz().setCourse(course);
        course.setQuizzes(List.of(value.getQuiz(), quizQuestionJoinTable.getQuiz()));
        DescriptiveQuestion descriptiveQuestion = DescriptiveQuestion.builder()
                .question("descriptive question")
                .description("descriptive question description")
                .title("descriptive question title")
                .build();
        descriptiveQuestion = descriptiveQuestionRepository.save(descriptiveQuestion);
        QuizQuestionJoinTable quizQuestionJoinTable2 = QuizQuestionJoinTable.builder()
                .quiz(value.getQuiz())
                .question(descriptiveQuestion)
                .score(0.5)
                .id(new QuizQuestionKey(value.getQuiz().getId(), descriptiveQuestion.getId()))
                .build();
        descriptiveQuestion.setQuizzes(List.of(quizQuestionJoinTable2));
        TestQuestion testQuestion = TestQuestion.builder()
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .title("test question title")
                .build();
        testQuestion = testQuestionRepository.save(testQuestion);
        QuizQuestionJoinTable quizQuestionJoinTable3 = QuizQuestionJoinTable.builder()
                .quiz(quizQuestionJoinTable.getQuiz())
                .question(testQuestion)
                .score(0.5)
                .id(new QuizQuestionKey(quizQuestionJoinTable.getQuiz().getId(), testQuestion.getId()))
                .build();
        testQuestion.setQuizzes(List.of(quizQuestionJoinTable3));
        List<DescriptiveQuestion> descriptiveQuestions = repository().getQuestions(course.getId(), DescriptiveQuestion.class);
        assertThat(descriptiveQuestions).isEqualTo(List.of(quizQuestionJoinTable.getQuestion(), descriptiveQuestion));
    }
}
