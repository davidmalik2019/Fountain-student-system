package com.fisnig.springBootCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisnig.springBootCrud.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
