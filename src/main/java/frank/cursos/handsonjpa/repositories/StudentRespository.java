package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class StudentRespository {

    @Autowired
    EntityManager manager;

    public Student findById(Long id){
        return manager.find(Student.class,id);
    }

    public void deleteById(Long id){
        manager.remove(findById(id));
    }

    public Student save(Student student){
        Optional<Long> optional = Optional.ofNullable(student.getId());
        optional.ifPresentOrElse(i -> manager.merge(student), ()->manager.persist(student));
        return student;
    }
}
