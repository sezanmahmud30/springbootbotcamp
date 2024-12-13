package com.springbootbotcamp.springbootbotcamp.restcontroller;


import com.springbootbotcamp.springbootbotcamp.entity.Students;
import com.springbootbotcamp.springbootbotcamp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

http://localhost:8080/api/students/save

@RestController
@RequestMapping("/api/students")

public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<List<Students>> getAllStudents(){

        List<Students> studentsList = studentService.getAllStudents();

        return ResponseEntity.ok(studentsList);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStudents(@RequestBody Students s){

        studentService.saveStudents(s);


        return new ResponseEntity<>("Student saved Successfully", HttpStatus.CREATED);
    }


}
