package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTestMvc {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean
    private StudentService studentService;

    @SpyBean
    private AvatarService avatarService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testAddStudent() throws Exception {
        Long id = 11L;
        String name = "test";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Long id = 11L;
        String name = "test";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Long id = 11L;
        String name = "test";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        doNothing().when(studentRepository).delete(any(Student.class));


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + student.getId())
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindStudents() throws Exception {
        Long id = 11L;
        String name = "test";
        int age = 21;

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student);

        when(studentRepository.findAllStudentByAge(any(Integer.class))).thenReturn(students);

        mockMvc.perform(
                        get("http://localhost:8080/student/getAllStudentsByAge")
                                .param("age", String.valueOf(age))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$").isArray()) // Проверка, что ответ является массивом
                .andExpect(jsonPath("$.length()").value(students.size())) // Проверка длины массива
                .andExpect(jsonPath("$[0].name").value("test")); // Проверка содержимого
    }

    @Test
    public void testGetAllStudents() throws Exception {
        Long id = 11L;
        String name = "test";
        int age = 21;

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student);

        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(
                        get("http://localhost:8080/student/getAllStudents")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$").isArray()) // Проверка, что ответ является массивом
                .andExpect(jsonPath("$.length()").value(students.size())) // Проверка длины массива
                .andExpect(jsonPath("$[0].name").value("test")); // Проверка содержимого
    }

    @Test
    public void testGetAllStudentsBetween() throws Exception {
        Long id = 11L;
        String name = "test";
        int age = 21;

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student);

        when(studentRepository.findByAgeBetween(any(Integer.class), any(Integer.class))).thenReturn(students);

        mockMvc.perform(
                        get("http://localhost:8080//student/getAllStudentsBetween")
                                .param("min", String.valueOf(age))
                                .param("max", String.valueOf(age))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$").isArray()) // Проверка, что ответ является массивом
                .andExpect(jsonPath("$.length()").value(students.size())) // Проверка длины массива
                .andExpect(jsonPath("$[0].name").value("test")); // Проверка содержимого
    }

    @Test
    public void testGetStudent() throws Exception {
        Long id = 11L;
        String name = "test";
        int age = 21;

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        when(studentRepository.getReferenceById(any(Long.class))).thenReturn(student);

        mockMvc.perform(
                        get("http://localhost:8080/student/11")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    public void findStudentsOfFaculty() throws Exception{
        Long id = 11L;
        String name = "test";
        int age = 21;

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student);


        when(studentRepository.findAllStudentByFacultyId(any(Long.class))).thenReturn(students);

        mockMvc.perform(
                        get("http://localhost:8080/student/getAllStudentsOfFaculty")
                                .param("facultyId", String.valueOf(id))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Проверка статуса ответа
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("test"));
    }


}
