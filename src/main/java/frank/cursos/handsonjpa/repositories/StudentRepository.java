package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.entities.Passport;
import frank.cursos.handsonjpa.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Repository
//Crean un contexto de persistencia con el que se va a ejecutar el metodo, y maneja el metodo como una Transaccion
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager manager;

    public void dummyMethod() {
        //find ejecutado del manager trae student al contexto
        Student student = manager.find(Student.class, 14L);
        student.setName("Updated");
        //Ejecuta los querys correspondientes a las acciones realizadas hasta el momento del flush; sin embargo
        //si sucede un error antes de terminar el metodo (Transaccion)
        //se hace un rollback de los querys ejecutados por los flush
        manager.flush();
        //El persistence Context se encarga de crear los querys y traer los objetos de la Base de Datos
        Passport passport = student.getPassport();
        //Deja de hacer Track a la entidad que se pasa por parametro
        //manager.detach(passport);
        passport.setNumber("No Update");
    }

    public Student findById(Long id) {
        return manager.find(Student.class, id);
    }

    public void deleteById(Long id) {
        manager.remove(findById(id));
    }

    public Student save(Student student) {
        Optional<Long> optional = Optional.ofNullable(student.getId());
        optional.ifPresentOrElse(i -> manager.merge(student), () -> manager.persist(student));
        return student;
    }

    public void retrieveStudentAndCoursesHarcore(){
        Student student = manager.find(Student.class,21L);
        log.info("Student {}",student);
        log.info("Courses {}",student.getCourses());
    }



    public void addStudentCoursePersist(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);
    }

    public void addStudentCourseHardcore(){
        Course course = new Course("JPA2.2");
        Student student = Student.builder().name("Flanky").build();
        Student student2 = Student.builder().name("Splanky").build();
        student.addCourse(course);
        student2.addCourse(course);
        course.addStudent(student);
        course.addStudent(student2);
        manager.persist(student);
        manager.persist(course);
        manager.persist(student2);

    }

    public void addStudentToIdCourse(Long id){
        Course course = manager.find(Course.class, id);
        Student student = Student.builder().name("Planky").build();
        course.addStudent(student);
        student.addCourse(course);
        manager.persist(student);
    }


}