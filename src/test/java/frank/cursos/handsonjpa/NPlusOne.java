package frank.cursos.handsonjpa;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.repositories.rest.CourseRepositoryData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@SpringBootTest
public class NPlusOne {

    @Autowired
    CourseRepositoryData repositoryData;

    @Autowired
    EntityManager manager;

    //Por cada course hace un select para buscar sus students
    //Problema ejecuta muchos querys
    @Test
    @Transactional
    void n_plus_one() {
        List<Course> courses = repositoryData.findAll();
        courses.forEach(course -> log.info("Course {}, Sutdent {}", course, course.getStudents()));
    }

    //En un solo query hace el join con los students
    //Ocupa EntityGraph y Subgraph (mediante NamedQuery)
    @Test
    @Transactional
    void n_plus_one_solve1() {
        EntityGraph<Course> entityGraph = manager.createEntityGraph(Course.class);
        Subgraph<Object> subgraph = entityGraph.addSubgraph("students");

        List<Course> courses = manager
                .createNamedQuery("find_all",Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        courses.forEach(course -> log.info("Course {}, Student {}", course, course.getStudents()));
    }

    //Ocupa JOIN FETCH (mediante NamedQuery)
    @Test
    void n_plus_one_solve2() {
        List<Course> courses = manager.createNamedQuery("all_courses_join_fetch",Course.class).getResultList();
        courses.forEach(course -> log.info("Course {}, Student {}", course, course.getStudents()));
    }
}
