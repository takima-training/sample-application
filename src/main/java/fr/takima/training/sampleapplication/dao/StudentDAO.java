package fr.takima.training.sampleapplication.dao;

import fr.takima.training.sampleapplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO extends JpaRepository<Student, Long> {
    List<Student> findStudentsByDepartment_Name(String departmentName);
    int countAllByDepartment_Name(String departmentName);
    Student getById(long id);
}
