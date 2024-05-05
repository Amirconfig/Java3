package ca.sheridancollege.khanmoam.repositories;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;
import ca.sheridancollege.khanmoam.beans.Course;
import lombok.Data;

@Component
@Data
public class CourseListImpl implements CourseList {

    private List<Course> courseList = new CopyOnWriteArrayList<>();

    public void emptyList() {
        courseList.clear();
    }
}
