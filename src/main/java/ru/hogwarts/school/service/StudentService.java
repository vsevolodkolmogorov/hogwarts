package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    StudentService(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public Student findStudent(long id) {
        return repository.getReferenceById(id);
    }

    public List<Student> findAllStudentsBetween(Integer min, Integer max) {
        return repository.findByAgeBetween(min, max);
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<Student> findLastFiveStudents() {
        return repository.findLastFiveStudents();
    }

    public Integer findAllStudentsCount() {
        return repository.findAllStudentCount();
    }

    public Double findAllStudentsAvgAge() {
        return repository.findAllStudentsAvgAge();
    }

    public Student updateStudent(Student student) {
        return repository.save(student);
    }

    public void deleteStudent(long id) {
        repository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        return repository.findAllStudentByAge(age);
    }

    public Faculty findStudentFaculty(long id) {
        return findStudent(id).getFaculty();
    }

    public Collection<Student> findStudentsOfFaculty(long facultyId) {
        return repository.findAllStudentByFacultyId(facultyId);
    }
}
