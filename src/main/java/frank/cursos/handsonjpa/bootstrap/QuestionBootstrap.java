package frank.cursos.handsonjpa.bootstrap;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.entities.Passport;
import frank.cursos.handsonjpa.entities.Student;
import frank.cursos.handsonjpa.repositories.CourseRepository;
import frank.cursos.handsonjpa.repositories.PassportRespository;
import frank.cursos.handsonjpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Order(4)
public class QuestionBootstrap implements CommandLineRunner {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    PassportRespository passportRespository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("============================ QUESTION BOOTSTRAP =====================================");

        Course course1=courseRepository.save(new Course("JPA11.1"));
        Course course2=courseRepository.save(new Course("JPA22.2"));

        Passport passport1 = passportRespository.save(new Passport("2A1234"));
        Passport passport2 = passportRespository.save(new Passport("1B2345"));

        Student student1 = studentRepository.save(new Student("Student1",passport1));
        Student student2 = studentRepository.save(new Student("Student2",passport2));

        studentRepository.addStudentCoursePersist(student1,course1);
        studentRepository.addStudentCoursePersist(student1,course2);
        studentRepository.addStudentCoursePersist(student2,course1);
        studentRepository.addStudentCoursePersist(student2,course2);
    }
}