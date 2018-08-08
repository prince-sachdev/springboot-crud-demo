package com.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Student;
import com.demo.service.StudentManager;

@RestController
@RequestMapping("/studentsApi")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	StudentManager studentManager;
	
	@RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getStudents(){
		logger.info("Received the request for getting all student records successfully.");
		List<Student> students = studentManager.getAllStudents();
		if(students == null){
			return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
		}
		logger.info("Sending all the student records in the response.");
		return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/students/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getStudentsByName(@PathVariable("name") String name){
		logger.info("Received the request for getting all student record by student name.");
		List<Student> students = studentManager.getStudentsByName(name);
		if(students == null){
			return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
		}
		logger.info("Sending all the student records by student name in the response.");
		return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudent(@PathVariable("studentId") Integer studentId){
		logger.info("Received the request for getting a student record by student id.");
		Student student = studentManager.getStudent(studentId);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		logger.info("Sending the student record by student id in the response.");
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		logger.info("Received the request for adding a student record.");
		Student stu = studentManager.createStudent(student);
		logger.info("Sending the created student record in the response.");
		return new ResponseEntity<Student>(stu, HttpStatus.CREATED);
				
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> updateStudent(@RequestBody Student student){
		logger.info("Received the request for updating a student record.");
		if (student == null || student.getId() == null) {
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		Student stu = studentManager.updateStudent(student);
		if (stu == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		logger.info("Sending the updated student record in the response.");
		return new ResponseEntity<Student>(stu, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Integer studentId){
		logger.info("Received the request for deleting a student record.");
		if (studentId == null){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		String msg = studentManager.deleteStudent(studentId);
		logger.info("Sending the no content in the response.");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
