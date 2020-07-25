package frank.cursos.handsonjpa.repositories.rest;

import frank.cursos.handsonjpa.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepositoryData extends JpaRepository<Student,Long> {

}
