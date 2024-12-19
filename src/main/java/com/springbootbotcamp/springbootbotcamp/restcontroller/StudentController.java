package com.springbootbotcamp.springbootbotcamp.restcontroller;


import com.springbootbotcamp.springbootbotcamp.entity.Students;
import com.springbootbotcamp.springbootbotcamp.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//http://localhost:8080/api/students/save

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
    public ResponseEntity<String> saveStudents
            (@RequestPart Students s,
             @RequestParam(value="image",required=true) MultipartFile file) throws IOException {

        studentService.saveStudents(s,file);


        return new ResponseEntity<>("Student saved Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteStudents(@PathVariable  int id) {

        try {
            studentService.deleteStudents(id);
            return ResponseEntity.ok("Student with this ID " + id + " has been Deleted");

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Students> updateStudents(@PathVariable int id,@RequestBody Students s){

        Students updateStudent = studentService.updateStudent(id, s);

        return ResponseEntity.ok(updateStudent);
    }
    @GetMapping("/{email}")
    public ResponseEntity<Students> findStudentByEmail(@PathVariable String email){

        Students updateStudent = studentService.findStudentByEmail(email);

        return ResponseEntity.ok(updateStudent);

    }


}