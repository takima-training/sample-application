package fr.takima.training.sampleapplication.IT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class StudentControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql({"/CreateSchema.sql", "/InsertData.sql"})
    void testGetStudentById() throws Exception {
        mockMvc.perform(get("/students/6"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("id", equalTo(6)))
            .andExpect(jsonPath("firstname", equalTo("Jeanne")))
            .andExpect(jsonPath("lastname", equalTo("Ausecours")))
            .andExpect(jsonPath("department.id", equalTo(4)))
            .andExpect(jsonPath("department.name", equalTo("GC")));
    }

    @Test
    @Sql({"/CreateSchema.sql", "/InsertData.sql"})
    void testGetNonExistingStudentById() throws Exception {
        mockMvc.perform(get("/students/666"))
            .andExpect(status().isNotFound());
    }

    @Test
    @Sql({"/CreateSchema.sql", "/InsertData.sql"})
    void testPostStudent() throws Exception {
        String body = "{\n" +
                "    \"firstname\": \"Didier\",\n" +
                "    \"lastname\": \"Deschamps\",\n" +
                "    \"department\": {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"GC\"\n" +
                "    }\n" +
                "}";
        mockMvc.perform(post("/students/")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }

    @Test
    @Sql({"/CreateSchema.sql", "/InsertData.sql"})
    void testPostStudentWithoutLastName() throws Exception {
        String body = "{\n" +
                "    \"firstname\": \"Didier\",\n" +
                "    \"department\": {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"GC\"\n" +
                "    }\n" +
                "}";
        mockMvc.perform(post("/students/")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql({"/CreateSchema.sql", "/InsertData.sql"})
    void testPostStudentWithoutDepartment() throws Exception {
        String body = "{\n" +
                "    \"lastname\": \"Didier\",\n" +
                "    }\n" +
                "}";
        mockMvc.perform(post("/students/")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql({"/CreateSchema.sql", "/InsertData.sql"})
    void testUpdateStudent() throws Exception {
        mockMvc.perform(get("/students/77"))
            .andExpect(jsonPath("id", equalTo(77)))
            .andExpect(jsonPath("firstname", equalTo("Magee")))
            .andExpect(jsonPath("lastname", equalTo("Buchanan")))
            .andExpect(jsonPath("department.id", equalTo(5)))
            .andExpect(jsonPath("department.name", equalTo("GM")));

        String body = "{\n" +
                "    \"firstname\": \"Francis\",\n" +
                "    \"lastname\": \"Huster\",\n" +
                "    \"department\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"ASI\"\n" +
                "    }\n" +
                "}";
        mockMvc.perform(put("/students/77")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", equalTo(77)))
                .andExpect(jsonPath("firstname", equalTo("Francis")))
                .andExpect(jsonPath("lastname", equalTo("Huster")))
                .andExpect(jsonPath("department.id", equalTo(1)))
                .andExpect(jsonPath("department.name", equalTo("ASI")));
    }

    @Test
    @Sql({"/CreateSchema.sql", "/InsertData.sql"})
    void testDeleteStudent() throws Exception {
        mockMvc.perform(get("/students/1"))
            .andExpect(jsonPath("id", equalTo(1)));
        mockMvc.perform(delete("/students/1"))
            .andExpect(status().isOk());
        mockMvc.perform(get("/students/1"))
            .andExpect(status().isNotFound());
    }
}
