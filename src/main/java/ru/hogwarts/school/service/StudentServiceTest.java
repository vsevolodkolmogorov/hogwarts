package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import java.util.Collection;
import java.util.List;

@Profile("test")
@Service
public class StudentServiceTest implements StudentService{

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceProduction.class);

    StudentServiceTest() {}

    public Student createStudent(Student student) {
        logger.info("Was invoked method createStudent");
        logger.debug("Student created {}", student);
        return student;
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method findStudent");
        Student student = new Student();
        student.setId(id);
        student.setName("test");
        logger.debug("Student was founded {}", student);
        return student;
    }

    public List<Student> findAllStudentsBetween(Integer min, Integer max) {
        logger.info("Was invoked method findAllStudentsBetween");
        Student student = new Student();
        student.setId(1L);
        student.setName("test");
        List<Student> list = List.of(student);
        logger.debug("Students between {} {} was founded {}", min, max, list);
        return list;
    }

    public List<Student> findAllStudents() {
        logger.info("Was invoked method findAllStudents");
        Student student = new Student();
        student.setId(1L);
        student.setName("test");
        List<Student> list = List.of(student);
        logger.debug("Students was founded {}", list);
        return list;
    }

    public List<Student> findLastFiveStudents() {
        logger.info("Was invoked method findLastFiveStudents");
        Student student = new Student();
        student.setId(1L);
        student.setName("test");
        List<Student> list = List.of(student);
        logger.debug("Last 5 student was founded {}", list);
        return list;
    }

    public Integer findAllStudentsCount() {
        logger.info("Was invoked method findAllStudentsCount");
        Integer count = 1;
        logger.debug("Students count was founded {}", count);
        return count;
    }

    public Double findAllStudentsAvgAge() {
        logger.info("Was invoked method findAllStudentsAvgAge");
        Double avgAge = 1.0;
        logger.debug("Students average age was founded {}", avgAge);
        return avgAge;
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method updateStudent");
        logger.debug("Student updated {}", student);
        return student;
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method deleteStudent");
        Student student = new Student();
        student.setId(id);
        student.setName("test");
        logger.debug("Student deleted {}", student);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method findByAge");
        Student student = new Student();
        student.setId(1L);
        student.setName("test");
        List<Student> list = List.of(student);
        logger.debug("Students by age {} found {}", age, list);
        return list;
    }

    public Faculty findStudentFaculty(long id) {
        logger.info("Was invoked method findStudentFaculty");
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("test");
        logger.debug("Faculty found {}", faculty);
        return faculty;
    }

    public Collection<Student> findStudentsOfFaculty(long facultyId) {
        logger.info("Was invoked method findStudentsOfFaculty");
        Student student = new Student();
        student.setId(1L);
        student.setName("test");
        List<Student> list = List.of(student);
        logger.debug("Students by facultyId {} found {}", facultyId, list);
        return list;
    }
}
