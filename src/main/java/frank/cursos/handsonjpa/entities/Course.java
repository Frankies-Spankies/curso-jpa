package frank.cursos.handsonjpa.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "find_all", query = "SELECT c from Course c"),
        @NamedQuery(name = "find_where", query = "Select c From Course c Where c.name like 'JPA%'"),

}) // Solo se puede tener una sola anotacion de NameQuery; si se necesitan mas hay que agruparlos en la anotacion @NameQuerys
public class Course {

    @Id
    @GeneratedValue // Si no se usa una estrategia los indices de los id de entidades distintas siguen la numeracion, p.e en Passport puede empezar apartir del 15
    private Long id;
    @Column(length = 1000)
    private String name;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    public Course(String name) {
        this.name = name;
    }
}
