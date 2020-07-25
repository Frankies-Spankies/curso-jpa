package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Course;
import frank.cursos.handsonjpa.entities.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;


@Slf4j
@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ReviewRespository reviewRespository;

    @Test
    @Transactional
    void retrieveReviewsForCourses() {
        Course course = courseRepository.findById(1L);
        List<Review> reviews = course.getReviews();
        log.info("Reviews {}", reviews);

    }

    @Test
    void retrieveCouseFromAReview() {
        Review review = reviewRespository.findById(18L);
        log.info("Course {}",review.getCourse());
    }
}