package frank.cursos.handsonjpa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"reviews", "students"})
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "find_all", query = "SELECT c from Course c"),
        @NamedQuery(name = "find_where", query = "Select c From Course c Where c.name like 'JPA%'"),
        @NamedQuery(name = "all_courses_join_fetch", query = "SELECT c from Course c JOIN FETCH c.students s")

}) // Solo se puede tener una sola anotacion de NameQuery; si se necesitan mas hay que agruparlos en la anotacion @NameQuerys
//La entidad podra ser cacheada, en el segundo nivel de cache, se podra poner y obtener
//L2C puts unidades puestas
//L2C hits unidades encontradas
//L2C misses unidades no encontradas
@Cacheable
//Query que se ejecuta al borrar una entidad
//Hay que agregar un metodo con @PreRemove
//para desactivar el campo "active" en la entidad que esta en cache
@SQLDelete(sql = "update course set active=false where id=?")
//Clausula agregada a los select de la entidad
//No funciona en native querys por lo que hay que poner la clausula en estos querys
@Where(clause = "active=true")
@Entity
@Table(indexes = @Index(name = "idx_course_name", columnList = "name"))
public class Course {

    @Id
    @GeneratedValue // Si no se usa una estrategia los indices de los id de entidades distintas siguen la numeracion, p.e en Passport puede empezar apartir del 15
    public Long id;

    @Column(length = 1000)
    public String name;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new LinkedList<>();

    @CreationTimestamp
    public LocalDateTime created;

    @UpdateTimestamp
    public LocalDateTime updated;

    @JsonBackReference
    @ManyToMany(mappedBy = "courses")
    public List<Student> students = new LinkedList<>();

    public boolean active;

    @PreRemove
    public void preRemove(){
        this.active = false;
    }

    public Course(String name) {
        this.name = name;
        this.active = true;
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public void removeReview(Review review){
        this.reviews.remove(review);
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }
}
