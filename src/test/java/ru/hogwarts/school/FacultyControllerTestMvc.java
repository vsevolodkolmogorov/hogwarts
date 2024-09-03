package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTestMvc {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void testFindAllFaculty() throws Exception{
        Long id = 11L;
        String name = "test";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        ArrayList<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);

        when(facultyRepository.findAll()).thenReturn(faculties);

        mockMvc.perform(
                        get("http://localhost:8080/faculty/getAllFaculty")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$").isArray()) // Проверка, что ответ является массивом
                .andExpect(jsonPath("$.length()").value(faculties.size())) // Проверка длины массива
                .andExpect(jsonPath("$[0].name").value("test")); // Проверка содержимого
    }

    @Test
    public void testGetFacultyById() throws Exception{
        Long id = 11L;
        String name = "test";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.getReferenceById(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(
                        get("http://localhost:8080/faculty/" + id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$.name").value("test")); // Проверка содержимого
    }

    @Test
    public void testAddFaculty() throws Exception {
        Long id = 11L;
        String name = "test";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Long id = 11L;
        String name = "test";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long id = 11L;
        String name = "test";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        doNothing().when(facultyRepository).delete(any(Faculty.class));


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + faculty.getId())
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFacultiesByColor() throws Exception{
        Long id = 11L;
        String name = "test";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        ArrayList<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);

        when(facultyRepository.findAllFacultyByColor(any(String.class))).thenReturn(faculties);

        mockMvc.perform(
                        get("http://localhost:8080/faculty")
                                .param("color", color)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("test"));
    }

    @Test
    public void testGetFacultiesByColorOrName() throws Exception{
        Long id = 11L;
        String name = "test";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        ArrayList<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);

        when(facultyRepository.findAllFacultyByColorIgnoreCaseOrNameIgnoreCase((any(String.class)),(any(String.class)))).thenReturn(faculties);

        mockMvc.perform(
                        get("http://localhost:8080/faculty/getAllFacultiesByColorOrName")
                                .param("color", color)
                                .param("name", name)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("test"));
    }
}
