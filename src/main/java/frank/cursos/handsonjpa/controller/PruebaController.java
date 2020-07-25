package frank.cursos.handsonjpa.controller;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.entities.Passport;
import frank.cursos.handsonjpa.entities.Student;
import frank.cursos.handsonjpa.repositories.PassportRespository;
import frank.cursos.handsonjpa.repositories.rest.CourseRepositoryData;
import frank.cursos.handsonjpa.repositories.rest.StudentRepositoryData;
import frank.cursos.handsonjpa.repositories.rest.service.CashExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("prueba")
public class PruebaController {
    @Autowired
    EntityManager manager;

    @Autowired
    StudentRepositoryData studentRepositoryData;

    @Autowired
    CashExample cashExample;

    @Autowired
    PassportRespository passportRespository;

    @Autowired
    CourseRepositoryData courseRepositoryData;

    //@Transactional
    @GetMapping("/student")
    public ResponseEntity<Student> prueba(){
        Student student = studentRepositoryData.findById(15L).get();
       /* Student regreso = new Student();
        regreso.setId(student.getId());regreso.setName(student.getName());*/
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/student/courses")
    public ResponseEntity<List<Course>> prueba_student_courses(){
        Student student = studentRepositoryData.findById(21L).get();
        List<Course> courses = student.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<Course> pruebacourse(){
        Course course = cashExample.findCourseByIdWrapperCall().get();
        log.info("Course inside course {}", course);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    @GetMapping("/allcourse")
    public ResponseEntity<List<Course>> prueba_all_course(){
        List<Course> courses = courseRepositoryData.findAll();
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }


    @GetMapping("/passport")
    public ResponseEntity<Passport> pruebapassport(){
        Passport passport = passportRespository.findById(7L);
        return new ResponseEntity<>(passport, HttpStatus.OK);
    }




}
