package com.jalian.maktabfinalproject.controller;

import com.jalian.maktabfinalproject.entity.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController extends BasicController {

    @GetMapping("/{studentId}/courses")
    public List<CourseDto> getCourses(@PathVariable String studentId) throws Exception {
        Student student = get(studentId, studentService);
        return student.getCourses().stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{studentId}/quizzes")
    public List<QuizDto> getAllowedQuizzes(@PathVariable String studentId) throws Exception {
        isValid(studentId, studentService);
        return quizService.getAllowedQuizzes(get(studentId, studentService)).stream().map(quiz -> modelMapper.map(quiz, QuizDto.class)).collect(Collectors.toList());
    }

    @PutMapping("/{studentId}/participating-in-a-test/{quizId}")
    public List<Question> participatingInAQuiz(@PathVariable String studentId, @PathVariable Long quizId) throws Exception {
        isValid(studentId, studentService);
        Quiz quiz = get(quizId, quizService);
        if(!quizService.getAllowedQuizzes(get(studentId, studentService)).contains(quiz)) {
            throw new Exception("not found");
        }
        studentQuizService.joinedAQuiz(studentId, quizId);
        List<Question> questions = new ArrayList<>(testQuestionService.getQuestions(quizId));
        questions.addAll(descriptiveQuestionService.getQuestions(quizId));
        /*Timer timer = new Timer();
        long delay = (quiz.getTime() + 1) * 60 * 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handleTimerFinished(true);
            }
        }, delay);*/
        return questions;
    }

    /*public void handleTimerFinished(boolean timerFinished) {
        if (timerFinished) {
            throw new RuntimeException("Time limit exceeded!");
        }
    }*/

    @PostMapping("/{studentId}/quiz/{quizId}")
    public void receiveAnswers(@PathVariable String studentId, @PathVariable Long quizId, @RequestBody List<Answer> answers) throws Exception {
        if (isQuizEnded(quizId)) {
            throw new Exception("Quiz has ended. Answers cannot be submitted.");
        }
        isValid(studentId, studentService);
        Quiz quiz = get(quizId, quizService);
        if(!quizService.getAllowedQuizzes(get(studentId, studentService)).contains(quiz)) {
            throw new Exception("not found");
        }
        answers.forEach(answer -> {if (answer instanceof TestAnswer) {
        testAnswerService.save((TestAnswer) answer);}
        else if (answer instanceof DescriptiveAnswer) {
            descriptiveAnswerService.save((DescriptiveAnswer) answer);
        }
        });
    }

    private boolean isQuizEnded(Long quizId) throws Exception {
        Quiz quiz = get(quizId, quizService);
        int quizDurationMinutes = quiz.getTime();
        LocalTime endTime = LocalTime.of(0, quizDurationMinutes);
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(endTime);
    }
}
