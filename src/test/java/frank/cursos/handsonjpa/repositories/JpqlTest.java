package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@SpringBootTest
public class JpqlTest {

    @Autowired
    EntityManager manager;

    @Test
    void courses_without_students() {
        TypedQuery<Course> query = manager.createQuery("SELECT c from Course c where c.students.size = 0", Course.class);
        List<Course> result = query.getResultList();
        log.info("Courses without students {}",result);
    }

    @Test
    void courses_with_atleast_two_students() {
        TypedQuery<Course> query = manager.createQuery("select c from Course c where c.students.size >= 2", Course.class);
        List<Course> courses = query.getResultList();
        log.info("Courses with atleast two students {}", courses);
    }

    @Test
    void courses_order_by_size_students_ascending() {
        TypedQuery<Course> query = manager.createQuery("select c from Course c order by size (c.students)", Course.class);
        List<Course> courses = query.getResultList();
        log.info("Courses in ascendent order {}", courses);
    }

    @Test
    void courses_order_by_size_students_descending() {
        TypedQuery<Course> query = manager.createQuery("select c from Course c order by size (c.students) desc", Course.class);
        List<Course> courses = query.getResultList();
        log.info("Courses in ascendent order {}", courses);
    }

    @Test
    void students_with_passports_in_certain_pattern() {
        TypedQuery<Student> query = manager.createQuery("select s from Student s where s.passport.number like '%234%' ", Student.class);
        List<Student> students = query.getResultList();
        log.info("Students with passport with pattern 234 {}", students);
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"Flanky", "Planky"})
    void join_course_student(String nombre) {
        Query query = manager.createQuery("select c, s from Course c JOIN c.students s where s.name = :nombre ");
        query.setParameter("nombre",nombre);
        List<Object[]> result = query.getResultList();
        log.info("Tamaño {} ", result.size());
        result.forEach(e-> log.info("Course {} Student {} ", e[0], e[1]));
    }

    @Test
    void left_join_course_student() {
        Query query = manager.createQuery("select c, s from Course c left join c.students s");
        List<Object[]> result = query.getResultList();
        log.info("Tamaño {} ", result.size());
        result.forEach(e-> log.info("Course {} Student {} ", e[0], e[1]));
    }

    @Test
    void cross_join_course_student() {
        Query query = manager.createQuery("select c, s from Course c, Student s");
        List<Object[]> result = query.getResultList();
        log.info("Tamaño {} ", result.size());
        result.forEach(e -> log.info("Course {} Student {} ", e[0], e[1]));
    }
}
