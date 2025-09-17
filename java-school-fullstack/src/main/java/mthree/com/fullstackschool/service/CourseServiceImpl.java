package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.h2.store.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE

    @Autowired
    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        try{
            return courseDao.getAllCourses();
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to fetch courses from database", ex);
        }

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE

        try{
            return courseDao.findCourseById(id);
        } catch (DataAccessException ex){
            throw new RuntimeException("Course not found with id: " + id, ex);
        }

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        try {
            return courseDao.createNewCourse(course);
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to create new course", ex);
        }

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

        try {
            courseDao.updateCourse(course);
            return course;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Unable to update course with id: " +id, ex);
        }

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

        try {
            courseDao.deleteCourse(id);
        } catch (DataAccessException ex){
            throw new RuntimeException("Unable to delete course with id: "+id, ex);
        }


        //YOUR CODE ENDS HERE
    }
}
