package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
        studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        teacherMap.put(teacher.getName(),teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            List<String>studentList = teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
            studentList.add(student);
            teacherStudentMapping.put(teacher,studentList);
        }
    }

    public Student findStudent(String student){
        // your code goes here
        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.get(teacher);
    }

    public List<String> findAllStudents(){
        // your code goes here
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        teacherMap.remove(teacher);

        List<String> studentsToRemove = teacherStudentMapping.get(teacher);
        if (studentsToRemove != null) {
            for (String student : studentsToRemove) {
                studentMap.remove(student);
            }
        }

        // Remove teacher from teacherStudentMapping
        teacherStudentMapping.remove(teacher);
    }

    public void deleteAllTeachers(){
        // your code goes here
        teacherMap.clear();
        teacherStudentMapping.clear();
        studentMap.clear();
    }
}