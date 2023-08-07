Objective
The objective of this project is to implement a web application for creating and conducting online exams. The project generally includes the following features:
Registration as a professor/student
Approval of user registration by the manager
Searching and filtering student/professor records
Definition of a course (Course) by the manager
Adding a professor to a course by the manager
Adding a student to a course by the manager
Definition of an exam by a professor
Adding multiple-choice and descriptive questions to an exam
Question bank
Setting a default score for each question in an exam
Ability to conduct an exam
Ability to grade students (automatically and manually)
Roles of manager, professor, and student

Phase A
Registration as a professor/student
There should be a facility for two roles of professor and student so that both of them can register in the system. The user; should specify whether he/she wants to register as a student or a professor at the time of registration. After registration, this user is in the "Pending Approval" status.
Approval of user registration by the manager
The manager should have a list of all registered individuals in the entire system, along with the necessary information. After a successful user registration, the user with the role of manager will approve the registered user and the user in question will change to the "Approved" status. The manager can also edit user information and change their role.
Searching and filtering student/professor records
The manager should be able to apply filters on the desired list based on various parameters such as user role (professor/student), name, last name, etc., and search and display all records that meet the specified conditions.
Definition of a course (Course) by the manager
The user with the role of manager can view the list of all courses (Courses) and also define a new course. The information for each course should at least include the following:
Course title
Unique course ID
Course start date
Course end date ...
Adding a professor/student to a course by the manager
Each course will have only one professor who, after registering in the system, must be added by the manager as a professor of a specific course to that course. A professor can teach in multiple courses.
Other user privileges with the role of manager include the ability to add students to a specific course. Deletion and editing options should also be considered.
The manager should also be able to view the list of all individuals (professor/student) who are present in a specific course by viewing the list of courses.

Phase B
Definition of an exam by a professor
The user with the role of professor, after logging in to the web application, should be able to view the list of courses in which he/she teaches and has been added to by the manager. The professor, by selecting each course, should be able to view the list of exams for that course.
Upon entering each course, he/she should be able to view a list of all exams that he/she has created for that course previously. He/she can also edit them or delete an exam. In this section, he/she can also create a new exam.
By selecting "Add New Exam", and then entering a title for the exam, some description about the exam, and the time required for that exam in minutes, and finally selecting "Save Exam", he/she can create a new exam.
After the exam is successfully created, the list of exams for the course should be updated.

Phase C
Adding multiple-choice and descriptive questions to an exam
After selecting "Edit Exam", the professor can now add questions to the exam in two ways:
Using the question bank: In this section, all questions that have been previously designed by the professor are visible, and the professor can add one or more questions from them to the exam.
Adding a new question to the exam
The purpose of this section is to design a section where the user "professor" can, after selecting "Add Question", select the type of question.
There are two types of questions:
Multiple-choice question: The professor should be able to add as many options as he/she wants to the question by clicking on the "Add new option" button. There should be no limit on the number of options, and the correct answer should also be specified. (The number of options for a question is not specified in advance.)
Descriptive question: In this case, a text will be considered as the question, and a space will be provided for the student

Phase D
Viewing the list of enrolled courses by a student
The user with the role of student, after logging in, should be able to see the list of courses in which he/she has been enrolled by the manager.
The student, after entering the desired course, should be able to see a list of exams that he/she is eligible to participate in. By selecting "Take Exam", he/she can participate in the exam and the exam will start immediately.
Please note that if the user has previously participated in an exam, he/she is not authorized to participate in that exam again.

Phase E
Grading an exam
The professor should be able to update his/her page and see the number and names of the people who have participated in an exam and the results of the students who have completed the exam.
For exams with multiple-choice questions, the score should be calculated automatically at the end of the exam, and for descriptive questions, the professor should be able to enter the desired score for the exam of each student by selecting the exam page of each student.
It is clear that the score that the professor gives for a descriptive answer should not be more than the default score set for that question.
Conducting an exam
When the exam starts, a countdown timer should be displayed to calculate the remaining time and display it at all times.
The questions should be displayed to the student, and by clicking on the next button, the next question should be displayed. When the student reaches the last question, and by selecting the "Submit" button, the exam ends.
The student should be able to return to previous questions before the end of the exam time, and if necessary, change the answer.
Please note that if the time expires, the exam will end automatically. Naturally, only the questions that the student has answered will be recorded, and the other questions will be recorded as unanswered, and no points will be awarded for them.
It is also possible that the student's connection to the web server may be interrupted for any reason during the exam. Therefore, it is necessary to consider a mechanism so that the questions that the student has answered before the connection is interrupted are temporarily stored somewhere and can be retrieved and displayed to the student when he/she reconnects in the same session. Of course, the student should not be able to continue answering the exam after the connection is restored.

Conclusion
This is a brief overview of the features of the online exam web application project. The project is still under development, and more features will be added in the future.
