package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
@SpringBootTest
public class CriteriaQuerys {

    @Autowired
    EntityManager manager;

    @Test
    void basic_criteria_query() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> from = cq.from(Course.class);

        TypedQuery<Course> query = manager.createQuery(cq.select(from));
        List<Course> results = query.getResultList();
        log.info("Result {}", results);
    }

    @Test
    void courses_JPA2() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> from = cq.from(Course.class);
        Predicate like = cb.like(from.get("name"), "JPA2%");

        TypedQuery<Course> query = manager.createQuery(cq.select(from).where(like));
        List<Course> result = query.getResultList();
        log.info("Results {}", result);

    }

    @Test
    void all_courses_without_students() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> from = cq.from(Course.class);
        Predicate isEmpty = cb.isEmpty(from.get("students"));

        TypedQuery<Course> query = manager.createQuery(cq.select(from).where(isEmpty));
        List<Course> result = query.getResultList();
        log.info("Results {}", result);
    }

    @Test
    void join_courses_students() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> from = cq.from(Course.class);
        from.join("students");

        TypedQuery<Course> query = manager.createQuery(cq.select(from));
        List<Course> result = query.getResultList();
        log.info("Results {}", result);
    }

    @Test
    void left_join_courses_students() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> from = cq.from(Course.class);
        from.join("students", JoinType.LEFT);

        TypedQuery<Course> query = manager.createQuery(cq.select(from));
        List<Course> result = query.getResultList();
        log.info("Results {}", result);
    }
}
