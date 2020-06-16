package frank.cursos.handsonjpa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String rating;

    private String description;

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }
}
