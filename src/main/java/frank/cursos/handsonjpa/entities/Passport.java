package frank.cursos.handsonjpa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"student"})
@ToString(exclude = {"student"})
@NoArgsConstructor
@Entity // Puede que no tenga nada que ver pero hay que poner esta anotacion siempre debajo de la clase
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    @JsonBackReference
    @OneToOne(fetch=FetchType.LAZY, mappedBy = "passport")
    private Student student;

    public Passport(String number) {
        this.number = number;
    }

}
