package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public class CourseRepository {

    @Autowired
    EntityManager manager;

    public Course findById(Long id) {
        return manager.find(Course.class, id);
    }


    public void deleteById(Long id) {
        Course course = findById(id);
        manager.remove(course);
    }


    public Course save(Course course){
        Optional<Long> id = Optional.ofNullable(course.getId());
        id.ifPresentOrElse(i ->manager.merge(course),()-> manager.persist(course));
        return course;
    }


}
