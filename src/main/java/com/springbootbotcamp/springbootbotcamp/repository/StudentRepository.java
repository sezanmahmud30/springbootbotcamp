package com.springbootbotcamp.springbootbotcamp.repository;

import com.springbootbotcamp.springbootbotcamp.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {


    Optional<Students> findByEmail(String Email);

}
