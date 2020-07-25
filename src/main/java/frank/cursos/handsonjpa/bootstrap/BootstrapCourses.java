package frank.cursos.handsonjpa.bootstrap;

import frank.cursos.handsonjpa.entities.*;
import frank.cursos.handsonjpa.repositories.CourseRepository;
import frank.cursos.handsonjpa.repositories.PassportRespository;
import frank.cursos.handsonjpa.repositories.ReviewRespository;
import frank.cursos.handsonjpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Order(1)
public class BootstrapCourses implements CommandLineRunner {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PassportRespository passportRespository;

    @Autowired
    ReviewRespository reviewRespository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("########################################################################################################################################################");
        System.out.println("##############################################################################   Courses   #############################################################");
        Course course1=courseRepository.save(new Course("JPA1"));
        Course course2=courseRepository.save(new Course("JPA2"));
        Course course3=courseRepository.save(new Course("JPA3"));
        Course course4=courseRepository.save(new Course("JPA4"));
        Course course5=courseRepository.save(new Course("JPA5"));

        Passport passport1=passportRespository.save(new Passport("A1234"));
        Passport passport2=passportRespository.save(new Passport("B2345"));
        Passport passport3=passportRespository.save(new Passport("C7890"));
        Passport passport4=passportRespository.save(new Passport("D3457"));
        Passport passport5=passportRespository.save(new Passport("E0123"));

        Student student1 =studentRepository.save(new Student("Franki",passport1));
        Student student2 =studentRepository.save(new Student("Panki",passport2));
        Student student3 =studentRepository.save(new Student("Spanki",passport3));
        Student student4 =studentRepository.save(new Student("Flank",passport4));
        Student student5 =new Student("Frank",passport5);
        student5.setAddress(new Address("Number", "Street", "City"));
        studentRepository.save(student5);


        Review review1 = Review.builder().rating(ReviewRating.FIVE).description("Good").build();
        Review review2 = Review.builder().rating(ReviewRating.FOUR).description("Enought").build();
        Review review3 = Review.builder().rating(ReviewRating.FIVE).description("Excelent").build();
        Review review4 = Review.builder().rating(ReviewRating.TWO).description("Not what i was expected").build();
        Review review5 = Review.builder().rating(ReviewRating.THREE).description("Just fine").build();

        courseRepository.addReviewForCourseGeneral(1L,review1,review2,review3);
        courseRepository.addReviewForCourseGeneral(2L,review4,review5);

        studentRepository.addStudentCoursePersist(student1,course1);
        studentRepository.addStudentCoursePersist(student1,course2);
        studentRepository.addStudentCoursePersist(student2,course1);
        studentRepository.addStudentCoursePersist(student2,course2);

        studentRepository.addStudentCourseHardcore();
        studentRepository.retrieveStudentAndCoursesHarcore();

        studentRepository.addStudentToIdCourse(1L);

        IntStream.rangeClosed(1,10).forEach(i->courseRepository.save(new Course("Dummy"+i)));




    }
}
