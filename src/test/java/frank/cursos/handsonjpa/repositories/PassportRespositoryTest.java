package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Passport;
import frank.cursos.handsonjpa.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class PassportRespositoryTest {

    @Autowired
    EntityManager manager;

    @Test
    @Transactional
    void getStudent() {
        Passport passport = manager.find(Passport.class,7L);
        Student student = passport.getStudent();
        log.info("passport {} ->",passport);
        log.info("student {} ->",passport.getStudent());

    }
}