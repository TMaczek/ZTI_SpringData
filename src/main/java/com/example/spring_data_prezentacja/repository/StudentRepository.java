package com.example.spring_data_prezentacja.repository;

import com.example.spring_data_prezentacja.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainsIgnoreCase(String str);


    @Query(value = "SELECT * from student s where s.id = :id", nativeQuery=true) //student - nazwa tabeli
    List<Student> customQueryNative(@Param("id") Long userId);

    @Query("SELECT s from Student s where s.id = :id") // Student - nazwa klasy
    Optional<List<Student>> customQueryJPQL(@Param("id") Long userId);
    

}
