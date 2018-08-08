package com.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.controllers.StudentController;
import com.demo.dao.AddressRepository;
import com.demo.dao.StudentRepository;
import com.demo.model.Address;
import com.demo.model.Student;

@Service("studentManager")
public class StudentManagerImpl implements StudentManager {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	AddressRepository addressRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	public Student createStudent(Student student) {
		// TODO Auto-generated method stub
		System.out.println(student);
		Address address = student.getAddress();
		addressRepository.save(address);
		logger.info("Saving the student record.");
		return (Student) studentRepository.save(student);		
	}

	public Student getStudent(Integer id) {
		// TODO Auto-generated method stub
		Student student = (Student) studentRepository.findOne(id);
		//Address address = addressRepository.findOne(student.getAddress().getId());
		System.out.print(student);
		//student.setAddress(address);
		logger.info("Finding the student record by student id.");
		return student;
	}

	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		Student stu = (Student) studentRepository.findOne(student.getId());
		if (stu == null){
			return null;
		}
		stu.setAddress(student.getAddress());
		stu.setName(student.getName());
		stu.setStandard(student.getStandard());
		logger.info("Updating the student record.");
		return (Student) studentRepository.save(stu);
	}

	public String deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		studentRepository.delete(id);
		logger.info("Deleting the student record.");
		return "Deleted student with student id " + id + " Successfully.";
	}

	public List<Student> getStudentsByName(String name) {
		// TODO Auto-generated method stub
		logger.info("Getting the student records by student name.");
		return studentRepository.findByName(name);

	}

	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> studentList = new ArrayList<Student>();
		Iterator<Student> studentIterator = studentRepository.findAll().iterator();
		while (studentIterator.hasNext()) {
			studentList.add(studentIterator.next());
		}
		logger.info("Getting all the student records.");
		return studentList;
	}

}
