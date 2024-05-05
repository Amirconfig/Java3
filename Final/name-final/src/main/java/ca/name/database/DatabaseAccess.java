package ca.name.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.name.beans.Course;
import ca.name.beans.Student;

@Repository
public class DatabaseAccess {

	private NamedParameterJdbcTemplate jdbcTemplate;

    public DatabaseAccess(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }

    public Map<String, Object> getStudentById(Long id) {
        String sql = "SELECT * FROM students WHERE id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);
        Student student = jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper<>(Student.class));
        String sql2 = "SELECT * FROM courses WHERE studentId = :id";
        List<Course> courses = jdbcTemplate.query(sql2, parameters, new BeanPropertyRowMapper<>(Course.class));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", student.getId());
        result.put("number", student.getNumber());
        result.put("name", student.getName());
        result.put("courses", courses);
        return result;
    }
    
    public List<Course> getCourseByStudentId(long id){
    	String sql = "SELECT * FROM courses WHERE studentId = :id";
    	MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(Course.class));
    }
    
    public int insertCourse(Course course) {
    	String sql = "INSERT INTO courses (name, grade, studentId) VALUES (:name, :grade, :studentId)";

    	MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", course.getName());
        params.addValue("grade", course.getGrade());
        params.addValue("studentId", course.getStudentId());

        return jdbcTemplate.update(sql, params);
    }
}
