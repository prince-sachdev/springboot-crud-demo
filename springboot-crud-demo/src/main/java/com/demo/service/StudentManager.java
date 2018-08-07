package com.demo.service;

import java.util.List;
import com.demo.model.Student;

public interface StudentManager {

	public Student createStudent(Student student);
	public Student getStudent(Integer id);
	public Student updateStudent(Student student);
	public String deleteStudent(Integer id);
	public List<Student> getStudentsByName(String name);
	public List<Student> getAllStudents();
}
