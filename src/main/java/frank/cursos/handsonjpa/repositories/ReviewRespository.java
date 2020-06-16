package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class ReviewRespository {

    @Autowired
    EntityManager manager;

    public Review findById(Long id){
        return manager.find(Review.class,id);
    }

    public void deleteById(Long id){
        manager.remove(findById(id));
    }

    public Review save(Review review){
        Optional<Long> optional = Optional.ofNullable(review.getId());
        optional.ifPresentOrElse(i -> manager.merge(review), ()->manager.persist(review));
        return review;
    }
}
