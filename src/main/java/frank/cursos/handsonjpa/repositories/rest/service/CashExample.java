package frank.cursos.handsonjpa.repositories.rest.service;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.repositories.rest.CourseRepositoryData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CashExample {

    @Autowired
    CourseRepositoryData repositoryData;

    public Optional<Course> findCourseByIdWrapper(Long id){
        Optional<Course> course = repositoryData.findById(id);
        log.info("Course inside the wrapper {}", course);
        return course;
    }

    @Transactional
    public Optional<Course> findCourseByIdWrapperCall(){
       Optional<Course> course = repositoryData.findById(1L);
       log.info("Course first call {}", course);
       Optional<Course> returned = findCourseByIdWrapper(course.get().id);
       return returned;
    }
}
