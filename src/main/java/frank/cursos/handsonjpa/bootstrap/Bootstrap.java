package frank.cursos.handsonjpa.bootstrap;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.entities.Passport;
import frank.cursos.handsonjpa.entities.Review;
import frank.cursos.handsonjpa.entities.Student;
import frank.cursos.handsonjpa.repositories.CourseRepository;
import frank.cursos.handsonjpa.repositories.PassportRespository;
import frank.cursos.handsonjpa.repositories.ReviewRespository;
import frank.cursos.handsonjpa.repositories.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRespository studentRespository;

    @Autowired
    PassportRespository passportRespository;

    @Autowired
    ReviewRespository reviewRespository;


    @Override
    public void run(String... args) throws Exception {
        courseRepository.save(new Course("JPA1"));
        courseRepository.save(new Course("JPA2"));
        courseRepository.save(new Course("JPA3"));
        courseRepository.save(new Course("JPA4"));
        courseRepository.save(new Course("JPA5"));

        Passport passport1=passportRespository.save(new Passport("A1234"));
        Passport passport2=passportRespository.save(new Passport("B2345"));
        Passport passport3=passportRespository.save(new Passport("C7890"));
        Passport passport4=passportRespository.save(new Passport("D3457"));
        Passport passport5=passportRespository.save(new Passport("E0123"));

        studentRespository.save(new Student("Franki",passport1));
        studentRespository.save(new Student("Panki",passport2));
        studentRespository.save(new Student("Spanki",passport3));
        studentRespository.save(new Student("Flank",passport4));
        studentRespository.save(new Student("Frank",passport5));


        reviewRespository.save(new Review("4","Good"));
        reviewRespository.save(new Review("4","Enought"));
        reviewRespository.save(new Review("5","Excelent"));
        reviewRespository.save(new Review("2","Not what i was expected"));
        reviewRespository.save(new Review("3","Just fine"));


    }
}
