package frank.cursos.handsonjpa.repositories.rest;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.repositories.rest.service.CashExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CourseRepositoryDataTest {

    @Autowired
    CashExample example;


    @Autowired
    CourseRepositoryData courseRepository;

    @Test
    void find_by_id_present() {
        Optional<Course> course = courseRepository.findById(1L);
        //log.info("{}",course.isPresent());
        assertTrue(course.isPresent());
    }

    @Test
    void find_by_id_not_present() {
        Optional<Course> course = courseRepository.findById(100L);
        //log.info("{}", course.isPresent());
        assertFalse(course.isPresent());

    }

    @Test
    void save_course() {
        Course course = new Course("JPA7");
        courseRepository.save(course);
        course.setName("JPA7-Updated");
        courseRepository.save(course);
    }

    @Test
    void all_courses() {
        log.info("Courses {}", courseRepository.findAll());
        log.info("Count {}", courseRepository.count());
    }

    @Test
    void sort() {
        Sort by = Sort.by(Sort.Direction.DESC, "name");
        List<Course> courses = courseRepository.findAll(by);
        log.info("Courser sorted {}", courses);
    }

    @Test
    void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Course> one = courseRepository.findAll(pageRequest);
        log.info("Page {}, elemnts {}", one.getNumber(), one.get().collect(Collectors.toList()));
        while (!one.isLast()) {
            Pageable next = one.nextPageable();
            one = courseRepository.findAll(next);
            log.info("Page {}, elemnts {}", one.getNumber(), one.get().collect(Collectors.toList()));
        }
    }

    @Test
    void findByNameNative() {
        log.info("FindByName {}",courseRepository.allCourses("JPA1"));
    }

    @Test
    void findByNameJpql() {
        log.info("FindByName {}", courseRepository.jpqlAllCourses("JPA1"));
    }

    @RepeatedTest(2)
    void first_level_course() {
        Optional<Course> course = example.findCourseByIdWrapperCall();
        log.info("Test of cash {}", course);
    }

    @Test
    void delete_by_id() {
        assertNotNull(courseRepository.findById(5l).get());
        courseRepository.deleteById(5l);

       courseRepository.findById(5l)
               .ifPresentOrElse(Assertions::assertNull
                       ,()->assertTrue(true));
       log.info("All courses {}", courseRepository.findAll());
    }
}