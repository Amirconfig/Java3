//CourseListImpl.java
package ca.sheridancollege.khanmoam.repositories;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;
import ca.sheridancollege.khanmoam.beans.Course;

@Component
public class CourseListImpl implements CourseList {

    private List<Course> courseList = new CopyOnWriteArrayList<>();

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void emptyList() {
        courseList.clear();
    }

}
