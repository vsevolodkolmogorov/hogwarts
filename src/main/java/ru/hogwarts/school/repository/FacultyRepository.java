package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    ArrayList<Faculty> findAllFacultyByColor(String color);
    ArrayList<Faculty> findAllFacultyByColorIgnoreCaseOrNameIgnoreCase(String color, String name);
}
