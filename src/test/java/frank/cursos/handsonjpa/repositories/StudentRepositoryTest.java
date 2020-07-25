package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Passport;
import frank.cursos.handsonjpa.entities.Student;
import frank.cursos.handsonjpa.repositories.rest.StudentRepositoryData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentRepositoryTest {

    @Mock
    Passport passport;

    @Autowired
    EntityManager manager;

    @Autowired
    StudentRepositoryData studentRepositoryData;

    @Autowired
    StudentRepository studentRepository;

    @Test
    @Transactional
    void retrieveStudentAndPassportDetails() {
        //Hasta aqui acaba la sesion del metodo findById
        Student student = manager.find(Student.class, 14L);
        // Hasta que se pone @Transactional se pueden recuperar los datos de Passport ya que no depende de la session del findById
        log.info(student.toString());
        log.info(student.getPassport().toString());

    }



    @Test
    @Transactional
    void conCrud() {
        studentRepositoryData.findById(14L)
                .flatMap(i->Optional.of(i.toString()))
                .ifPresentOrElse(log::info,()->log.info("no esta"));
    }

    @Test
    void dummy() {
        studentRepository.dummyMethod();
    }
    //todo - verficiar por que parace no funcionar la transaccion en este metodo

    @Test
    @Transactional
    void dummy2() {
        //Incluso aunque student logra traer al contexto de persistencia a Passport, necesita una session o un contexte para que se pueda inicializar, i.e @Transactional
        Student student = studentRepositoryData.findById(14L).get();
        student.setName("Updated");
        Passport passport = student.getPassport();
        passport.setNumber("No Update");
    }





}