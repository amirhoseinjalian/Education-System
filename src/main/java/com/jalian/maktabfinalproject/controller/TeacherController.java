package com.jalian.maktabfinalproject.controller;

import com.jalian.maktabfinalproject.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController extends BasicController {

    @PostMapping("{teacherId}/{courseId}/quizzes")
    public List<Quiz> createQuiz(@RequestBody QuizDto quizDto, @PathVariable String teacherId, @PathVariable Long courseId) throws Exception {
        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        if (!teacher.getCourses().contains(course)) {
            throw new Exception("toy do not have this course");
        }
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        return courseService.addQuiz(course, quiz);
    }

    @GetMapping("/{id}/courses")
    public List<CourseDto> getAllCourses(@PathVariable String id) throws Exception {
        Teacher teacher = get(id, teacherService);
        return teacher.getCourses().stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}/courses/quizzes")
    List<Quiz> getAllQuizzes(@PathVariable Long id) throws Exception {
        Course course = get(id, courseService);
        return courseService.getAllQuizzes(course);
    }

    @DeleteMapping("/courses/(id)")
    public void deleteQuiz(@PathVariable Long id) throws Exception {
        if (isValid(id, quizService))
            quizService.deleteById(id);
    }

    @PutMapping("/{teacherId}/{courseId}/quizzes")
    public QuizDto updateQuiz(@RequestBody QuizDto quizDto, @PathVariable String teacherId, @PathVariable Long courseId) throws Exception {
        if (quizDto.getId() == null) {
            throw new Exception("not found");
        }
        isValid(teacherId, teacherService);
        isValid(courseId, courseService);
        Quiz newQuiz = modelMapper.map(quizDto, Quiz.class);
        return modelMapper.map(quizService.updateQuiz(newQuiz), QuizDto.class);
    }

    @GetMapping("/{courseId}/quizzes/test-questions")
    public List<TestQuestion> getTestQuestionBank(@PathVariable Long courseId) throws Exception {
        if (isValid(courseId, courseService)) {
            return testQuestionService.questionBank(get(courseId, courseService));
        }
        throw new Exception("not found");
    }

    @GetMapping("/{courseId}/quizzes/des-questions")
    public List<DescriptiveQuestion> getDesQuestionBank(@PathVariable Long courseId) throws Exception {
        if (isValid(courseId, courseService)) {
            return descriptiveQuestionService.questionBank(get(courseId, courseService));
        }
        throw new Exception("not found");
    }

    @PostMapping("/{courseId}/quizzes/{quizId}/test-questions/{score}")
    public List<TestQuestion> addTestQuestion(@PathVariable(name = "courseId") Long courseId, @PathVariable(name = "quizId") Long quizId
            , @RequestBody TestQuestion testQuestion, @PathVariable Double score) throws Exception {

        isValid(courseId, courseService);
        Quiz quiz = get(quizId, quizService);
        if (!testQuestion.getOptions().contains(new Option(testQuestion.getAnswer().getId()))) {
            throw new Exception("answer not found");
        }
        quizService.addQuestion(quiz, testQuestion, score);
        /*//rah hal behtar izae!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        List<QuizQuestionJoinTable> questionJoinTables = quiz.getQuestions();
        List<Question> questions = new ArrayList<>();
        questionJoinTables.forEach(quizQuestionJoinTable -> questions.add(quizQuestionJoinTable.getQuestion()));
        List<TestQuestion> testQuestions = new ArrayList<>();
        questions.forEach(question -> {if(question instanceof TestQuestion) {testQuestions.add((TestQuestion) question);}});*/
        return testQuestionService.getQuestions(quiz);
    }

    @PostMapping("/{courseId}/quizzes/{quizId}/descriptive-questions/{score}")
    public List<DescriptiveQuestion> addDesQuestion(@PathVariable(name = "courseId") Long courseId, @PathVariable(name = "quizId") Long quizId
            , @RequestBody DescriptiveQuestion descriptiveQuestion, @PathVariable Double score) throws Exception {

        isValid(courseId, courseService);
        Quiz quiz = get(quizId, quizService);
        quizService.addQuestion(quiz, descriptiveQuestion, score);
        return descriptiveQuestionService.getQuestions(quiz);
    }

    @GetMapping("/{quizId}/students")
    public List<PersonDto> getStudentsOfAQuiz(@PathVariable Long quizId) throws Exception {
        return studentQuizService.getStudentsOfAQuiz(get(quizId, quizService)).stream().map(student -> modelMapper.map(student, PersonDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{quizId}/passed-students")
    public List<PersonDto> getPassedStudents(@PathVariable Long quizId) throws Exception {
        Quiz quiz = get(quizId, quizService);
        return studentQuizService.getStudentsOfAQuiz(quiz).stream().map(student -> modelMapper.map(student, PersonDto.class)).collect(Collectors.toList());
    }

    @PutMapping("/{studentId}/quizzes/{quizId}/test-questions/grade")
    public Map<String, Double> getTestQuestionGrades(@PathVariable String studentId, @PathVariable Long quizId) throws Exception {
        Student student = get(studentId, studentService);
        Quiz quiz = get(quizId, quizService);
        return quizService.correctTestQuestion(student, quiz);
    }
}
