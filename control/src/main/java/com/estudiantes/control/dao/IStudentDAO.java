package com.estudiantes.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudiantes.control.model.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface IStudentDAO extends JpaRepository<Student,String> {

	@Query(value="select * from t2_student limit 10", nativeQuery = true)
    public List<Student> getAllLimit();
}
