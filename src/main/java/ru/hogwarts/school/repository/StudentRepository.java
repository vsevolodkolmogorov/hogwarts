package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<Student, Long> {
    ArrayList<Student> findAllStudentByAge(Integer age);
    ArrayList<Student> findByAgeBetween(Integer minAge, Integer maxAge);
    ArrayList<Student> findAllStudentByFacultyId(Long id);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer findAllStudentCount();
    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Double findAllStudentsAvgAge();
    @Query(value = "SELECT * FROM student ORDER BY ID DESC LIMIT 5", nativeQuery = true)
    ArrayList<Student> findLastFiveStudents();
}
