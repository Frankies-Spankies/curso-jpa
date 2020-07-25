package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.entities.Review;
import frank.cursos.handsonjpa.entities.ReviewRating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Transactional
@Repository
public class CourseRepository {

    @Autowired
    EntityManager manager;

    public Course findById(Long id) {
        return manager.find(Course.class, id);
    }


    public void deleteById(Long id) {
        Course course = findById(id);
        manager.remove(course);
    }


    public Course save(Course course) {
        Optional<Long> id = Optional.ofNullable(course.getId());
        id.ifPresentOrElse(i -> manager.merge(course), () -> manager.persist(course));
        return course;
    }

    public void addReviewForCourseHardCore() {
        Course course = findById(1L);
        log.info("Reviews {}", course.getReviews());

        Review review = Review.builder().description("why not").rating(ReviewRating.FIVE).build();
        Review review1 = Review.builder().description("why not").rating(ReviewRating.FIVE).build();

        course.addReview(review);
        review.setCourse(course);

        course.addReview(review1);
        review1.setCourse(course);

        manager.persist(review);
        manager.persist(review1);
    }

    public void addReviewForCourseGeneral(Long courseId, Review ... reviews) {
        Course course = findById(courseId);
        log.info("Course Reviews {}",course.getReviews());

        Stream.of(reviews).forEach(review -> {
            course.addReview(review);
            review.setCourse(course);
            manager.persist(review);
        });
    }


}
