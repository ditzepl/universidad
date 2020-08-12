package com.estudiantes.control.service.serviceimpl;

import com.estudiantes.control.dao.IStudentDAO;
import com.estudiantes.control.model.Student;
import com.estudiantes.control.services.IStudent;

import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorDerbyDatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class StudentService implements IStudent {

    @Autowired
    private IStudentDAO studentDAO;

    @Override
    public Student add(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public void delete(String id) {
        studentDAO.deleteById(id);
    }

    @Override
    public Optional<Student> find(String id) {
        return studentDAO.findById(id);
    }

    @Override
    public List<Student> getAll() {
        //return studentDAO.findAll();
        return studentDAO.getAllLimit();
    }
}