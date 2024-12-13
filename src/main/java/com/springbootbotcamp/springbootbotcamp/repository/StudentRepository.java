package com.springbootbotcamp.springbootbotcamp.repository;

import com.springbootbotcamp.springbootbotcamp.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {



}
