package ru.hogwarts.school.service.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Profile("production")
@Service
public class StudentServiceProduction implements StudentService {
    private final StudentRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceProduction.class);

    StudentServiceProduction(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method createStudent");
        Student newStudent = repository.save(student);
        logger.debug("Student created {}", newStudent);
        return newStudent;
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method findStudent");
        Student student = repository.getReferenceById(id);
        logger.debug("Student was founded {}", student);
        return student;
    }

    public List<Student> findAllStudentsBetween(Integer min, Integer max) {
        logger.info("Was invoked method findAllStudentsBetween");
        List<Student> list = repository.findByAgeBetween(min, max);
        logger.debug("Students between {} {} was founded {}", min, max, list);
        return list;
    }

    public List<Student> findAllStudents() {
        logger.info("Was invoked method findAllStudents");
        List<Student> list  = repository.findAll();
        logger.debug("Students was founded {}", list);
        return list;
    }

    public List<Student> findLastFiveStudents() {
        logger.info("Was invoked method findLastFiveStudents");
        List<Student> list = repository.findLastFiveStudents();
        logger.debug("Last 5 student was founded {}", list);
        return list;
    }

    public Integer findAllStudentsCount() {
        logger.info("Was invoked method findAllStudentsCount");
        Integer count = repository.findAllStudentCount();
        logger.debug("Students count was founded {}", count);
        return count;
    }


    public Student updateStudent(Student student) {
        logger.info("Was invoked method updateStudent");
        Student updatedStudent = repository.save(student);
        logger.debug("Student updated {}", updatedStudent);
        return updatedStudent;
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method deleteStudent");
        Student student = repository.getReferenceById(id);
        repository.delete(student);
        logger.debug("Student deleted {}", student);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method findByAge");
        List<Student> list = repository.findAllStudentByAge(age);
        logger.debug("Students by age {} found {}", age, list);
        return list;
    }

    public Faculty findStudentFaculty(long id) {
        logger.info("Was invoked method findStudentFaculty");
        Faculty faculty = findStudent(id).getFaculty();
        logger.debug("Faculty found {}", faculty);
        return faculty;
    }

    public Collection<Student> findStudentsOfFaculty(long facultyId) {
        logger.info("Was invoked method findStudentsOfFaculty");
        List<Student> list = repository.findAllStudentByFacultyId(facultyId);
        logger.debug("Students by facultyId {} found {}", facultyId, list);
        return list;
    }

    public List<Student> findStudentsWithNamesThatStartWithA() {
        return repository.findAll().stream()
                .peek(student -> student.setName(student.getName().toUpperCase()))
                .filter(student -> student.getName().startsWith("A"))
                .sorted()
                .toList();
    }

    public Double findAllStudentsAvgAge() {
        return repository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average().getAsDouble();
    }

    @Override
    public void printParallel() {
        List<String> names = repository.findAll().stream().map(Student::getName).toList();

        System.out.println(names.get(0));
        System.out.println(names.get(1));

         new Thread(() -> {
            System.out.println(names.get(2));
            System.out.println(names.get(3));
        }).start();

         new Thread(() -> {
            System.out.println(names.get(4));
            System.out.println(names.get(5));
        }).start();
    }

    @Override
    public void printSynchronized() {
        List<String> names = repository.findAll().stream().map(Student::getName).toList();

        printName(names.get(0));
        printName(names.get(1));

        new Thread(() -> {
            printName(names.get(2));
            printName(names.get(3));
        }).start();

        new Thread(() -> {
            printName(names.get(4));
            printName(names.get(5));
        }).start();
    }

    public void printName(String name) {
        synchronized (StudentServiceProduction.class) {
            System.out.println(name);
        }
    }
}
