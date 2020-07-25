package frank.cursos.handsonjpa.bootstrap;

import frank.cursos.handsonjpa.repositories.PassportRespository;
import frank.cursos.handsonjpa.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
class BootstrapCoursesTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PassportRespository passportRespository;

    @Test
    void printStudents() {
        LongStream.rangeClosed(1L,5L)
                .mapToObj(studentRepository::findById)
                .forEach(System.out::println);
    }

    @Test
    void printPassports() {
        LongStream.rangeClosed(1L, 5L)
                .mapToObj(passportRespository::findById)
                .forEach(System.out::println);
    }
}