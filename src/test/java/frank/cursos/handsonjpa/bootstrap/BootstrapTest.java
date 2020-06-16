package frank.cursos.handsonjpa.bootstrap;

import frank.cursos.handsonjpa.repositories.PassportRespository;
import frank.cursos.handsonjpa.repositories.StudentRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
class BootstrapTest {

    @Autowired
    StudentRespository studentRespository;
    @Autowired
    PassportRespository passportRespository;

    @Test
    void printStudents() {
        LongStream.rangeClosed(1L,5L)
                .mapToObj(studentRespository::findById)
                .forEach(System.out::println);
    }

    @Test
    void printPassports() {
        LongStream.rangeClosed(1L, 5L)
                .mapToObj(passportRespository::findById)
                .forEach(System.out::println);
    }
}