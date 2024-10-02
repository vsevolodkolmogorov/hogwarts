package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.student.StudentServiceProduction;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceProduction.class);

    FacultyService(FacultyRepository facultyRepository) {
        this.repository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method createFaculty");
        return repository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method findFaculty");
        return repository.getReferenceById(id);
    }

    public List<Faculty> findAllFaculties() {
        logger.info("Was invoked method findAllFaculties");
        return repository.findAll();
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method updateFaculty");
        return repository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method deleteFaculty");
        repository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Was invoked method findByColor");
        return repository.findAllFacultyByColor(color);
    }

    public Collection<Faculty> findFacultiesByColorOrName(String color, String name) {
        logger.info("Was invoked method findFacultiesByColorOrName");
        return repository.findAllFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public String findLongestName() {
        return repository.findAll()
                .stream()
                .max(Comparator.comparing(faculty -> faculty.getName().length()))
                .get()
                .getName();
    }

    public Integer findSecretNum() {
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }

}
