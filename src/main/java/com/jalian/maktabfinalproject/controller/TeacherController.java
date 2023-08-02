package com.jalian.maktabfinalproject.controller;

import com.jalian.maktabfinalproject.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController extends BasicController {

    @PostMapping("{teacherId}/{courseId}/quizzes")
    public List<QuizDto> createQuiz(@RequestBody QuizDto quizDto, @PathVariable String teacherId, @PathVariable Long courseId) throws Exception {
        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        validationBetweenTeacherAndCourse(teacher, course);
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        quiz = quizService.save(quiz);
        courseService.addQuiz(course, quiz);
        return get(courseId, courseService).getQuizzes().stream().map(quiz1 -> modelMapper.map(quiz1, QuizDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}/courses")
    public List<CourseDto> getAllCourses(@PathVariable String id) throws Exception {
        Teacher teacher = get(id, teacherService);
        return teacher.getCourses().stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{teacherId}/courses/{courseId}/quizzes")
    List<QuizDto> getAllQuizzes(@PathVariable("teacherId") String teacherId, @PathVariable("courseId") Long id) throws Exception {
        Course course = get(id, courseService);
        Teacher teacher = get(teacherId, teacherService);
        validationBetweenTeacherAndCourse(teacher, course);
        return course.getQuizzes().stream().map(quiz -> modelMapper.map(quiz, QuizDto.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{teacherId}/courses/{courseId}/(id)")
    public void deleteQuiz(@PathVariable("teacherId") String teacherId, @PathVariable("courseId") Long courseId, @PathVariable Long id) throws Exception {
        Course course = get(id, courseService);
        Teacher teacher = get(teacherId, teacherService);
        Quiz quiz = get(id, quizService);
        validationBetweenTeacher_CourseAndQuiz(teacher, course, quiz);
        quizService.deleteById(id);
    }

    @PutMapping("/{teacherId}/courses/{courseId}/quizzes")
    public QuizDto updateQuiz(@RequestBody QuizDto quizDto, @PathVariable String teacherId, @PathVariable Long courseId) throws Exception {
        if (quizDto.getId() == null || quizDto.getId() == 0L || quizDto.getId() < 0) {
            throw new Exception("not found");
        }
        Course course = get(courseId, courseService);
        Teacher teacher = get(teacherId, teacherService);
        Quiz quiz = get(quizDto.getId(), quizService);
        validationBetweenTeacher_CourseAndQuiz(teacher, course, quiz);
        Quiz newQuiz = modelMapper.map(quizDto, Quiz.class);
        return modelMapper.map(quizService.updateQuiz(newQuiz), QuizDto.class);
    }

    @GetMapping("/{teacherId}/courses/{courseId}/quizzes/question-bank")
    public List<Question> questionBank(@PathVariable("teacherId") String teacherId, @PathVariable("courseId") Long courseId) throws Exception {
        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        validationBetweenTeacherAndCourse(teacher, course);
        List<Quiz> quizzes = course.getQuizzes();
        List<Question> questions = new ArrayList<>();
        quizzes.forEach(quiz -> questions.addAll(quizQuestionService.getQuestions(quiz)));
        return questions;
    }

    @GetMapping("/{teacherId}/courses/{courseId}/quizzes/test-questions-bank")
    public List<TestQuestion> getTestQuestionBank(@PathVariable String teacherId, @PathVariable Long courseId) throws Exception {
        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        validationBetweenTeacherAndCourse(teacher, course);
        return testQuestionService.questionBank(course);
    }

    @GetMapping("/{teacherId}/courses/{courseId}/quizzes/descriptive-questions-bank")
    public List<DescriptiveQuestion> getDesQuestionBank(@PathVariable String teacherId, @PathVariable Long courseId) throws Exception {
        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        validationBetweenTeacherAndCourse(teacher, course);
        return descriptiveQuestionService.questionBank(course);
    }

    @PostMapping("/{teacherId}/courses/{courseId}/quizzes/{quizId}/test-questions/{score}")
    public List<TestQuestion> addTestQuestion(@PathVariable String teacherId, @PathVariable(name = "courseId") Long courseId, @PathVariable(name = "quizId") Long quizId
            , @RequestBody TestQuestion testQuestion, @PathVariable Double score) throws Exception {

        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        Quiz quiz = get(quizId, quizService);
        validationBetweenTeacher_CourseAndQuiz(teacher, course, quiz);
        if (testQuestion.getId() != null) {
            testQuestion = testQuestionService.findById(testQuestion.getId()).get();
        } else {
            testQuestion = testQuestionService.save(testQuestion);
        }
        testQuestion.getAnswer().setQuestion(testQuestion);
        quizService.addQuestion(quiz, testQuestion, score);
        return testQuestionService.getQuestions(quiz);
    }

    @PostMapping("/{teacherId}/courses/{courseId}/quizzes/{quizId}/descriptive-questions/{score}")
    public List<DescriptiveQuestion> addDesQuestion(@PathVariable String teacherId, @PathVariable(name = "courseId") Long courseId, @PathVariable(name = "quizId") Long quizId
            , @RequestBody DescriptiveQuestion descriptiveQuestion, @PathVariable Double score) throws Exception {

        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        Quiz quiz = get(quizId, quizService);
        validationBetweenTeacher_CourseAndQuiz(teacher, course, quiz);
        if (descriptiveQuestion.getId() != null) {
            descriptiveQuestion = descriptiveQuestionService.findById(descriptiveQuestion.getId()).get();
        } else {
            descriptiveQuestion = descriptiveQuestionService.save(descriptiveQuestion);
        }
        descriptiveQuestion.getAnswer().setQuestion(descriptiveQuestion);
        quizService.addQuestion(quiz, descriptiveQuestion, score);
        return descriptiveQuestionService.getQuestions(quiz);
    }

    @GetMapping("/{teacherId}/courses/{courseId}/quizzes/{quizId}/students")
    public List<PersonDto> getStudentsOfAQuiz(@PathVariable String teacherId, @PathVariable(name = "courseId") Long courseId,
                                              @PathVariable Long quizId) throws Exception {

        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        Quiz quiz = get(quizId, quizService);
        validationBetweenTeacher_CourseAndQuiz(teacher, course, quiz);
        return studentQuizService.getStudentsOfAQuiz(quiz).stream().map(student -> modelMapper.map(student, PersonDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{teacherId}/courses/{courseId}/quizzes/{quizId}/passed-students")
    public List<PersonDto> getPassedStudents(@PathVariable String teacherId, @PathVariable(name = "courseId") Long courseId,
                                             @PathVariable Long quizId) throws Exception {

        Teacher teacher = get(teacherId, teacherService);
        Course course = get(courseId, courseService);
        Quiz quiz = get(quizId, quizService);
        validationBetweenTeacher_CourseAndQuiz(teacher, course, quiz);
        return studentQuizService.getStudentsOfAQuiz(quiz).stream().map(student -> modelMapper.map(student, PersonDto.class)).collect(Collectors.toList());
    }

    @PutMapping("{teacherId}/students/{studentId}/quizzes/{quizId}/test-questions/grade")
    public Map<String, Double> getTestQuestionGrades(@PathVariable String teacherId, @PathVariable String studentId, @PathVariable Long quizId) throws Exception {
        Student student = get(studentId, studentService);
        Teacher teacher = get(teacherId, teacherService);
        validationBetweenTeacherAndStudent(teacher, student);
        Quiz quiz = get(quizId, quizService);
        validationBetweenStudentAndQuiz(student, quiz);
        return quizService.correctTestQuestion(student, quiz);
    }
}
