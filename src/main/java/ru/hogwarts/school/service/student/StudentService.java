package ru.hogwarts.school.service.student;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
     Student createStudent(Student student);
     Student findStudent(long id);
     Student updateStudent(Student student);
     List<Student> findAllStudentsBetween(Integer min, Integer max);
     List<Student> findAllStudents();
     List<Student> findLastFiveStudents();
     Collection<Student> findByAge(int age);
     Collection<Student> findStudentsOfFaculty(long facultyId);
     Integer findAllStudentsCount();
     Double findAllStudentsAvgAge();
     void deleteStudent(long id);
     Faculty findStudentFaculty(long id);
     List<Student> findStudentsWithNamesThatStartWithA();
}
