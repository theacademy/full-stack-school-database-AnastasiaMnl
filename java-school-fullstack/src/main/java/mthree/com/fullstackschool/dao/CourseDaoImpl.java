package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.CourseMapper;
import mthree.com.fullstackschool.model.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course createNewCourse(Course course) {
        final String sql = "INSERT INTO course (courseCode, courseDesc, teacherId) VALUES (?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // YOUR CODE STARTS HERE
            ps.setString(1, course.getCourseName()); // courseName maps to courseCode
            ps.setString(2, course.getCourseDesc());
            if (course.getTeacherId() != 0) {
                ps.setInt(3, course.getTeacherId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            course.setCourseId(key.intValue());
        }

        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        final String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public Course findCourseById(int id) {
        final String sql = "SELECT * FROM course WHERE cid = ?";
        return jdbcTemplate.queryForObject(sql, new CourseMapper(), id);
    }

    @Override
    public void updateCourse(Course course) {
        final String sql = "UPDATE course SET courseCode = ?, courseDesc = ?, teacherId = ? WHERE cid = ?";
        jdbcTemplate.update(sql,
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTeacherId(),
                course.getCourseId()
        );
    }

    @Override
    public void deleteCourse(int id) {
        final String sql = "DELETE FROM course WHERE cid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAllStudentsFromCourse(int course_id) {
        final String sql = "DELETE FROM course_student WHERE course_id = ?";
        jdbcTemplate.update(sql, course_id);
    }
}