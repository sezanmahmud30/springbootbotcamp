package com.springbootbotcamp.springbootbotcamp.service;


import com.springbootbotcamp.springbootbotcamp.entity.Students;
import com.springbootbotcamp.springbootbotcamp.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Value("src/main/resources/static/images")

    private String uploadDir;

    public List<Students> getAllStudents() {

        return studentRepository.findAll();

    }


    public void saveStudents(Students s,MultipartFile imageFile) throws IOException {


        if(imageFile != null && !imageFile.isEmpty()) {
            String imageFileName = saveImage(imageFile,s);

            s.setImage(imageFileName);
        }
        studentRepository.save(s);

    }

    public Students getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with Id: " + id));


    }

    public void deleteStudentById(int id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with Id: " + id);
        }
        studentRepository.deleteById(id);
    }

    public Students updateStudent(int id, Students updateStudent) {
        Students existingStudents = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with Id: " + id));

        if (updateStudent.getName() != null) {
            existingStudents.setName(updateStudent.getName());

        }

        if (updateStudent.getEmail() != null) {
            existingStudents.setEmail(updateStudent.getEmail());

        }

        if (updateStudent.getCellNo() != null) {
            existingStudents.setCellNo(updateStudent.getCellNo());

        }

        return studentRepository.save(existingStudents);
    }

    public void updateStudent(Students updateStudent) {
        studentRepository.save(updateStudent);

    }

    public void deleteStudents(int id) {
        studentRepository.deleteById(id);
    }

    public Students findStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with Email: " + email));
    }

    private String saveImage(MultipartFile file, Students s) throws IOException {
        Path uploadPath = Paths.get(uploadDir + "/students");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }


        String fileName = s.getName() + "_" + UUID.randomUUID().toString();
        //Sezan Mahmud_gddsuhkjsdjxfu

        Path filePath=uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath);

        return fileName;

    }
}
