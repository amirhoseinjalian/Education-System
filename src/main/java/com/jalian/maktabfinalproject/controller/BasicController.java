package com.jalian.maktabfinalproject.controller;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.service.answer.descriptiveAnswer.DescriptiveAnswerService;
import com.jalian.maktabfinalproject.service.answer.testAnswer.TestAnswerService;
import com.jalian.maktabfinalproject.service.base.BaseService;
import com.jalian.maktabfinalproject.service.course.CourseService;
import com.jalian.maktabfinalproject.service.person.student.StudentService;
import com.jalian.maktabfinalproject.service.person.teacher.TeacherService;
import com.jalian.maktabfinalproject.service.question.descriptiveQuestion.DescriptiveQuestionService;
import com.jalian.maktabfinalproject.service.question.testQuestion.TestQuestionService;
import com.jalian.maktabfinalproject.service.quiz.QuizService;
import com.jalian.maktabfinalproject.service.quizQuestion.QuizQuestionService;
import com.jalian.maktabfinalproject.service.role.RoleService;
import com.jalian.maktabfinalproject.service.studentQuiz.StudentQuizService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Data
public class BasicController {

    @Autowired
    protected StudentService studentService;

    @Autowired
    protected TeacherService teacherService;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected CourseService courseService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected QuizService quizService;

    @Autowired
    protected TestQuestionService testQuestionService;

    @Autowired
    protected DescriptiveQuestionService descriptiveQuestionService;

    @Autowired
    protected StudentQuizService studentQuizService;

    @Autowired
    protected TestAnswerService testAnswerService;

    @Autowired
    protected DescriptiveAnswerService descriptiveAnswerService;

    @Autowired
    protected QuizQuestionService quizQuestionService;

    @PostMapping("/reg")
    public String registration(@RequestBody SignupDto signupDto) throws Exception {
        String type = roleService.findById(signupDto.getRole().getId()).get().getName().toString();
        switch (type) {
            case "ADMIN":
                throw new Exception("You are not admin");
            case "STUDENT":
                return studentService.save(modelMapper.map(signupDto, Student.class)).toString();
            case "TEACHER":
                return teacherService.save(modelMapper.map(signupDto, Teacher.class)).toString();
            default:
                throw new Exception("not found");
        }
    }

    @GetMapping("/")
    public String greeting() {
        return "WELLCOME";
    }

    public <Id, Entity extends BaseEntity<Id>, Service extends BaseService<Entity, Id>> boolean isValid(Id id, Service service) {
        return service.findById(id).isPresent();
    }

    public <Id, Entity extends BaseEntity<Id>, Service extends BaseService<Entity, Id>> Entity get(Id id, Service service) throws Exception {
        if (isValid(id, service)) {
            return service.findById(id).get();
        }
        throw new Exception("not found");
    }

    protected void validationBetweenTeacher_CourseAndQuiz(Teacher teacher, Course course, Quiz quiz) throws Exception {
        validationBetweenTeacherAndCourse(teacher, course);
        validationBetweenCourseAndQuiz(course, quiz);
    }

    protected void validationBetweenTeacherAndCourse(Teacher teacher, Course course) throws Exception {
        if (!teacher.getCourses().contains(course)) {
            throw new Exception("you do not have this course");
        }
    }

    protected void validationBetweenCourseAndQuiz(Course course, Quiz quiz) throws Exception {
        if (!course.getQuizzes().contains(quiz)) {
            throw new Exception("this course does not have this quiz");
        }
    }

    protected void validationBetweenTeacherAndStudent(Teacher teacher, Student student) throws Exception {
        if (!teacher.getStudents().contains(student)) {
            throw new Exception("you do not have this student");
        }
    }

    protected void validationBetweenStudentAndQuiz(Student student, Quiz quiz) throws Exception {
        Optional<StudentQuiz> studentQuiz = studentQuizService.findById(new StudentQuizKey(student.getId(), quiz.getId()));
        if (!studentQuiz.isPresent()) {
            throw new Exception("this student does not have this quiz");
        }
    }

    protected void validationBetweenQuizAndQuestion(Quiz quiz, Question question) throws Exception {
        Optional<QuizQuestionJoinTable> quizQuestionJoinTable = quizQuestionService.findById(
                new QuizQuestionKey(quiz.getId(), question.getId()));
        if (!quizQuestionJoinTable.isPresent()) {
            throw new Exception("this quiz does not have this question");
        }
    }
}
