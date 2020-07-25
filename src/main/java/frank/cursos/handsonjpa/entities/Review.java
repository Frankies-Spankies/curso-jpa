package frank.cursos.handsonjpa.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
public class Review implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReviewRating rating;

    private String description;

    @JsonManagedReference
    @ManyToOne
    private Course course;

    @Builder
    public Review(Long id, ReviewRating rating, String description, Course course) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.course = course;
    }
}
