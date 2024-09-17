package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    FacultyService(FacultyRepository facultyRepository) {
        this.repository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return repository.getReferenceById(id);
    }

    public List<Faculty> findAllFaculties() {
        return repository.findAll();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return repository.save(faculty);
    }

    public void deleteFaculty(long id) {
        repository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return repository.findAllFacultyByColor(color);
    }

    public Collection<Faculty> findFacultiesByColorOrName(String color, String name) {
        return repository.findAllFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

}
