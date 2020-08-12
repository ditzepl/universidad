package com.estudiantes.control.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.estudiantes.control.model.Student;
import com.estudiantes.control.service.serviceimpl.StudentService;

@Controller
@RequestMapping("/estudiantes")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("")
    public String index(Model model){
        /*retornar estudiantes*/
        model.addAttribute("students",studentService.getAll());

        return "student";
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException{
        response.setContentType("text/csv");
        String filename="estudiantes.csv";

        String headerKey ="Content-Disposition";
        String headerValue="attachment; filename=" + filename;

        response.setHeader(headerKey,headerValue);

        List<Student> listUsers = studentService.getAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"name","surname"};
        String[] nameMapping = {"name","surname"};
        
        csvWriter.writeHeader(csvHeader);
        for(Student st : listUsers) {
        	csvWriter.write(st,nameMapping);
        }
        csvWriter.close();
    }

    @PostMapping("/nuevo")
    public String addStudent(@RequestBody Student student, Model model){
        studentService.add(student);

        return "your_Action";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteStudent(@RequestParam String id){
        studentService.delete(id);
        return "your_Action";
    }

    @PutMapping("/{id}")
    public String updateStudent(@RequestParam String id, @RequestBody Student student){
        studentService.update(student);

        return "your_Action";
    }
}
