package com.institute.student.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.institute.student.beans.Student;
import com.institute.student.beans.StudentDAO;
import com.institute.student.utils.ResultResponse;


@Controller
@RequestMapping("/students")
public class StudentController {
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public List<Student> getStudents() {
		System.out.println("get students");
		return StudentDAO.getInstance().getStudentList();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	@ResponseBody
	public ResultResponse saveStudent(@RequestBody Student student) {		
		ResultResponse resultResponse = new ResultResponse();
		String message = "";
		int resultCode;
		try {
			if(student.getId() != 0 && student.getId() != null) {
				System.out.println("update student");
				message = StudentDAO.getInstance().updateStudent(student);
				resultCode = 200;
			}
			else{
				System.out.println("create student");
				message = StudentDAO.getInstance().createStudent(student);
				resultCode = 201;
			}
			
		} catch (Exception e) {
			message = e.getMessage();
			resultCode = 200;
		}
		resultResponse.setCode(resultCode);
		resultResponse.setMessage(message);
		return resultResponse;
	}

}
