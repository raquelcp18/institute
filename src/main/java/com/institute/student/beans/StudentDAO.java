package com.institute.student.beans;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	
    private List<Student> studentList;

    private static StudentDAO studentServiceInstance = null;

    private StudentDAO(){
    	studentList = new ArrayList<Student>();
    }

    public static StudentDAO getInstance() {
        if(studentServiceInstance == null) {
              studentServiceInstance = new StudentDAO();
              return studentServiceInstance;
        }
        else {
            return studentServiceInstance;
        }
    }

    public String createStudent(Student newStudent) {
    	Long studentId = new Long(studentList.size()+1);
    	newStudent.setId(studentId);
    	studentList.add(newStudent);
    	return "Student created successfuly";
    }

    public String updateStudent(Student newStudentVersion) {
    	for(int i=0; i<studentList.size(); i++) {
            Student oldStudentVersion = studentList.get(i);
            if(oldStudentVersion.getId().equals(newStudentVersion.getId())) {
            	studentList.set(i, newStudentVersion);
            	return "Student updated successfuly";
            }

        }
    	throw new Error("Failed when updating student "+newStudentVersion.getId()+", it was not found");
    }

    public String deleteStudent(Long studentId) {
    	for(int i=0; i<studentList.size(); i++) {
            Student oldStudentVersion = studentList.get(i);
            if(oldStudentVersion.getId().equals(studentId)){
              studentList.remove(i);
              return "Student deleted successfuly";
            }
        }
    	throw new Error("Failed when deleting student "+studentId+", it was not found");
    }

    public List<Student> getStudentList() {
    	return studentList;
    }

}
