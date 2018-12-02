package fr.takima.training.sampleapplication.service;

import fr.takima.training.sampleapplication.dao.StudentDAO;
import fr.takima.training.sampleapplication.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<Student> getStudentsByDepartmentName(String departmentName) {
        if (departmentName == null || departmentName.length() == 0) {
            throw new IllegalArgumentException("The department name must not be null or empty.");
        }

        return studentDAO.findStudentsByDepartment_Name(departmentName);
    }

    public int getStudentsNumberByDepartmentName(String departmentName) {
        if (departmentName == null || departmentName.length() == 0) {
            throw new IllegalArgumentException("The department name must not be null or empty.");
        }

        return studentDAO.countAllByDepartment_Name(departmentName);
    }

    public Student getStudentById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("The student ID must be positive.");
        }

        return studentDAO.getById(id);
    }

    public Student addStudent(Student student) {
        if (student.getLastname() == null || student.getLastname().length() == 0 || student.getDepartment() == null) {
            throw new IllegalArgumentException("You can't add a student without setting a lastname and a department ID");
        }

        return this.studentDAO.save(student);
    }

    public void removeStudentById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("The student ID must be positive.");
        }

        this.studentDAO.deleteById(id);
    }
}
