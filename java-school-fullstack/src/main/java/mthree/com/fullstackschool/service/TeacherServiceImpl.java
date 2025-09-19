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

        if (teacher.getTeacherFName() == null || teacher.getTeacherFName().isBlank()
                || teacher.getTeacherLName() == null || teacher.getTeacherLName().isBlank()) {

            teacher.setTeacherFName("First Name blank, teacher NOT added");
            teacher.setTeacherLName("Last Name blank, teacher NOT added");
            return teacher;
        }
        return teacherDao.createNewTeacher(teacher);

        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE

        if (teacher.getTeacherId() != id) {
            teacher.setTeacherFName("IDs do not match, teacher not updated");
            teacher.setTeacherLName("IDs do not match, teacher not updated");
            return teacher;
        }
        teacherDao.updateTeacher(teacher);
        return teacher;

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
