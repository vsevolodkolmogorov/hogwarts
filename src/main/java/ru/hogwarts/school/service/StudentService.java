package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
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

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public Student updateStudent(Student student) {
        return repository.save(student);
    }

    public void deleteStudent(long id) {
        repository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : repository.findAll()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }

}
