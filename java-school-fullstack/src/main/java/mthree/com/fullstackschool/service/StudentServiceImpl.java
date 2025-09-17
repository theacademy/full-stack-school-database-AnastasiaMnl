package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE

    @Autowired
    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        try {
            return studentDao.getAllStudents();
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to fetch students from database", ex);
        }

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return studentDao.findStudentById(id);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Student not found with id: " +id, ex);
        }

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        if (student.getStudentFirstName() == null || student.getStudentLastName().isBlank()) {
            throw new IllegalArgumentException("Student first name cannot be empty");
        }
        try {
            return studentDao.createNewStudent(student);
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to create new student, ex");
        }

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if (student.getStudentId() != id){
            throw new IllegalArgumentException("Student ID in path does not match student body");
        }
        try {
            studentDao.updateStudent(student);
            return student;
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to update student with id: "+id, ex);
        }

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        try {
            studentDao.deleteStudent(id);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Unable to delete student with id: "+id, ex);
        }

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        try {
            studentDao.deleteStudentFromCourse(studentId, courseId);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Unable to remove student "+ studentId + "from course " + courseId, ex);
        }

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        try {
            studentDao.addStudentToCourse(studentId, courseId);
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to add student " + studentId + "to course " + courseId);
        }

        //YOUR CODE ENDS HERE
    }
}
