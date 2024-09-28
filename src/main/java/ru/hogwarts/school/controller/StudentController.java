package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping()
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllStudents")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/getAllStudentsCount")
    public int findAllStudentsCount() {
        return studentService.findAllStudentsCount();
    }

    @GetMapping("/getAllStudentsAvgAge")
    public Double findAllStudentsAvgAge() {
        return studentService.findAllStudentsAvgAge();
    }

    @GetMapping("/getLastFiveStudents")
    public List<Student> findLastFiveStudents() {
        return studentService.findLastFiveStudents();
    }

    @GetMapping("/getAllStudentsBetween")
    public List<Student> findAllStudentsBetween(@RequestParam(required = false) Integer min,
                                                @RequestParam(required = false) Integer max) {
        return studentService.findAllStudentsBetween(min, max);
    }

    @GetMapping("/getAllStudentsByAge")
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            System.out.println("Age is " + age);
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }


    @GetMapping("/getAllStudentFaculty")
    public Faculty findStudentFaculty(@RequestParam(required = false) Long id) {
        return studentService.findStudentFaculty(id);
    }

    @GetMapping("/getAllStudentsOfFaculty")
    public Collection<Student> findStudentsOfFaculty(@RequestParam(required = false) long facultyId) {
        return studentService.findStudentsOfFaculty(facultyId);
    }
}
