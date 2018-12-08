package fr.takima.training.sampleapplication.unit;

import fr.takima.training.sampleapplication.SampleApplication;
import fr.takima.training.sampleapplication.dao.DepartmentDAO;
import fr.takima.training.sampleapplication.dao.StudentDAO;
import fr.takima.training.sampleapplication.entity.Department;
import fr.takima.training.sampleapplication.entity.Student;
import fr.takima.training.sampleapplication.service.DepartmentService;
import fr.takima.training.sampleapplication.service.StudentService;
import org.apache.catalina.startup.ContextConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContextConfig.class)
@SpringBootTest(classes = SampleApplication.class)
public class DepartmentsServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentDAO departmentDAO;

    private Department department = Department.builder().id(1L).name("DepartementTest").build();

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDepartmentByName() {
        when(departmentDAO.getDepartmentByName("DepartmentTest")).thenReturn(department);
        assertEquals(department, departmentDAO.getDepartmentByName("DepartmentTest"));
    }

    @Test
    public void testGetDepartmentByNameWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> departmentService.getDepartmentByName(null));
    }

    @Test
    public void testGetDepartmentByNameWithEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> departmentService.getDepartmentByName(""));
    }
}
