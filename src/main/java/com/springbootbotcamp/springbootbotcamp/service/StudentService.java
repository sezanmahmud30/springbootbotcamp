package com.springbootbotcamp.springbootbotcamp.service;


import com.springbootbotcamp.springbootbotcamp.entity.Students;
import com.springbootbotcamp.springbootbotcamp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Students> getAllStudents(){

        return studentRepository.findAll();

    }


    public void saveStudents(Students s){
        studentRepository.save(s);
    }

    public Students getStudentById(int id){

        return studentRepository.getById(id);

    }


}
