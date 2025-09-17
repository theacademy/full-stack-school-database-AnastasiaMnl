package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        try {
            return teacherDao.getAllTeachers();
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to fetch teachers from database");
        }

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return teacherDao.findTeacherById(id);
        } catch (DataAccessException ex){
            throw new RuntimeException("Teacher not found with id: "+ id, ex);
        }

        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE

        if (teacher.getTeacherFName() == null || teacher.getTeacherFName().isBlank() ){
            throw new IllegalArgumentException("Teacher's first name cannot be empty");
        }
        if (teacher.getTeacherLName() == null || teacher.getTeacherLName().isBlank()){
            throw new IllegalArgumentException("Teacher's last name cannot be empty");
        }
        try {
            return teacherDao.createNewTeacher(teacher);
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to create new teacher", ex);
        }

        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE

        if (teacher.getTeacherId() != id) {
            throw new IllegalArgumentException("Teacher id in path does not match teacher body");
        }
        try {
            teacherDao.updateTeacher(teacher);
            return teacher;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Unable to update teacher with id: "+ id, ex);
        }

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE

        try {
            teacherDao.deleteTeacher(id);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Unable to delete teacher with id: "+ id, ex);
        }


        //YOUR CODE ENDS HERE
    }
}
