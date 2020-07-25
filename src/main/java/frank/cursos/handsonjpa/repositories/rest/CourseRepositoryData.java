package frank.cursos.handsonjpa.repositories.rest;

import frank.cursos.handsonjpa.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(path="courses")
public interface CourseRepositoryData extends JpaRepository<Course, Long> {

    List<Course> findByNameOrderByCreated(String name);

    @Query(value = "select * from Course where name like :name", nativeQuery = true)
    List<Course> allCourses(String name);


    @Query("select c from Course c where c.name = :gato")
    List<Course> jpqlAllCourses(String gato);

    void deleteById(Long id);


}
