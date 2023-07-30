package com.jalian.maktabfinalproject.controller;

import com.jalian.maktabfinalproject.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends BasicController {

    @GetMapping("/waiting-users")
    public List<PersonDto> getAllWaitingPersons() {
        return studentService.getAllWaitingPersons().stream().map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/confirm-reg")
    public List<PersonDto> confirmUsers(@RequestBody List<String> ids) {
        ids.forEach(string -> {
            studentService.confirmUsersReg(string);////////////////////////////////////////////////////
        });
        return getAllWaitingPersons();
    }

    @PostMapping("/courses")
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        return modelMapper.map(courseService.save(modelMapper.map(courseDto, Course.class)), CourseDto.class);
    }

    @PutMapping("/add-teacher-to-course/{id}/{teacherId}")
    public Course addATeacherToACourse(@PathVariable Long id, @PathVariable String teacherId) throws Exception {
        Course course = get(id, courseService);
        Teacher teacher = get(teacherId, teacherService);
        courseService.addTeacher(course, teacher);
        return get(id, courseService);
    }

    @PutMapping("/add-students-to-course/{id}")
    public Course addAStudentsToACourse(@PathVariable Long id, @RequestBody List<String> studentsId) throws Exception {
        Course course = get(id, courseService);
        List<Student> students = new ArrayList<>();
        studentsId.forEach(s -> students.add(studentService.findById(s).get()));
        students.forEach(student -> courseService.addStudent(course, student));
        return get(id, courseService);
    }

    @GetMapping("/courses")
    public List<CourseDto> getAllCourses() {
        return courseService.findAll().stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/courses/{id}/persons")
    public List<PersonDto> getAllUsersInThisCourse(@PathVariable Long id) throws Exception {
        Course course = get(id, courseService);
        return courseService.getAllUsers(course).stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
    }

    //delete and update operations//////////////////////////////////////////////////////////////////////////////
}
