package frank.cursos.handsonjpa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"courses", "passport"})
@EqualsAndHashCode(exclude = {"passport","courses"})
@NoArgsConstructor
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    //Evita un loop infinito al crear un json
    //En este caso no se puede evitar el loop ya que la relacion con passport es bidireccional
    //Y la entidad propietaria simpre va a ser eagger
    @JsonManagedReference
    //Quita propiedades del envoltorio para que el json lo pueda parsear a la clase necesaria
    //Nescesaria cuando se sobrescribe el fetch
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;


    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    private List<Course> courses = new LinkedList<>();

    //Agrega los campos de addres a LA TABLA Courses, a nivel de entidad sigue separado
    @Embedded
    public Address address;

    @Builder
    public Student(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }


}
