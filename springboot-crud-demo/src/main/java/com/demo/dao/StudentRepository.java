package com.demo.dao;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

	public List<Student> findByName(String name);
}
