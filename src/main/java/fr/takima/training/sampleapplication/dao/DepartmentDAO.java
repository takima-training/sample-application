package fr.takima.training.sampleapplication.dao;

import fr.takima.training.sampleapplication.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDAO extends JpaRepository<Department, Long> {
    Department getDepartmentByName(String name);
}
