package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class PassportRespository {

    @Autowired
    EntityManager manager;

    public Passport findById(Long id){
        return manager.find(Passport.class,id);
    }

    public void deleteById(Long id){
        manager.remove(findById(id));
    }

    public Passport save(Passport passport){
        Optional<Long> optional = Optional.ofNullable(passport.getId());
        optional.ifPresentOrElse(i -> manager.merge(passport), ()->manager.persist(passport));
        return passport;
    }
}
