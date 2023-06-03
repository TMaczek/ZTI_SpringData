package com.example.spring_data_prezentacja.repository;

import com.example.spring_data_prezentacja.entity.Course;
import com.example.spring_data_prezentacja.entity.Teacher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByNameContainsIgnoreCase(String name, Pageable pageable);
}
