package com.jalian.maktabfinalproject.controller;

import com.jalian.maktabfinalproject.entity.BaseEntity;
import com.jalian.maktabfinalproject.entity.SignupDto;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.entity.Teacher;
import com.jalian.maktabfinalproject.service.answer.descriptiveAnswer.DescriptiveAnswerService;
import com.jalian.maktabfinalproject.service.answer.testAnswer.TestAnswerService;
import com.jalian.maktabfinalproject.service.base.BaseService;
import com.jalian.maktabfinalproject.service.course.CourseService;
import com.jalian.maktabfinalproject.service.person.student.StudentService;
import com.jalian.maktabfinalproject.service.person.teacher.TeacherService;
import com.jalian.maktabfinalproject.service.question.descriptiveQuestion.DescriptiveQuestionService;
import com.jalian.maktabfinalproject.service.question.testQuestion.TestQuestionService;
import com.jalian.maktabfinalproject.service.quiz.QuizService;
import com.jalian.maktabfinalproject.service.role.RoleService;
import com.jalian.maktabfinalproject.service.studentQuiz.StudentQuizService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/reg")
    public String registration(@RequestBody SignupDto signupDto) throws Exception {
        String type = signupDto.getRole().getName().toString();
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

    public <Id, T extends BaseEntity<Id>, R extends BaseService<T, Id>> boolean isValid(Id id, R r) {
        return r.findById(id).isPresent();
    }

    public <Id, T extends BaseEntity<Id>, R extends BaseService<T, Id>> T get(Id id, R r) throws Exception {
        if (isValid(id, r)) {
            return r.findById(id).get();
        }
        throw new Exception("not found");
    }
}
