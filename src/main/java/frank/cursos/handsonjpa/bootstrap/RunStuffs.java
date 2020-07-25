package frank.cursos.handsonjpa.bootstrap;

import frank.cursos.handsonjpa.repositories.CourseRepository;
import frank.cursos.handsonjpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class RunStuffs implements CommandLineRunner {

    @Autowired
    CourseRepository repository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        //repository.addReviewForCourse();
        //studentRepository.addStudentCourseHardcore();

    }
}
