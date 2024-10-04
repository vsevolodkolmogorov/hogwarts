package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTestRest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }


    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Bob");
        student.setAge(40);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Vsevolod");
        student.setAge(21);

        ResponseEntity<Student> response = this.restTemplate.exchange(
                "http://localhost:" + port + "/student",
                HttpMethod.PUT,
                new HttpEntity<>(student),
                Student.class
        );

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testDeleteStudent() {
        long id = 1L;

        ResponseEntity<Student> response = this.restTemplate.exchange(
                "http://localhost:" + port + "/student/" + id,
                HttpMethod.DELETE,
                null,
                Student.class
        );

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testFindStudents() {
        int age = 19;

        ResponseEntity<ArrayList<Student>> response = this.restTemplate.exchange(
                "http://localhost:" + port + "/student/getAllStudentsByAge?age=" + age,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        ArrayList<Student> students = response.getBody();
        Assertions.assertThat(students).isNotEmpty();
    }

    @Test
    public void testGetAllStudents() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/getAllStudents", String.class))
                .isNotNull();
    }

    @Test
    public void testGetAllStudentsBetween() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/getAllStudentsBetween?min=19&max=21", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudent() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
                .isNotNull();
    }

    @Test
    public void testGetAllStudentFaculty() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/getAllStudentFaculty?min=1", String.class))
                .isNotNull();
    }

    @Test
    public void testGetAllStudentsOfFaculty() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/getAllStudentsOfFaculty?min=1", String.class))
                .isNotNull();
    }

}
