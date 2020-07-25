package frank.cursos.handsonjpa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
//Al usar @MappedSuperclass Employee deja de ser tratado como entidad y solo es una referencia para los atributos en comun de sus subclases
//El lenguaje JPQL ya no lo trata como entidad, por lo que si queremos obtener los empleados, tendremos que hacer el join de manera manual
@MappedSuperclass
//Cuando se agrega @MappedSuperclass ya no se puede usar la anotacion @Entity
//@Entity
//Esta anotacion permite que Employee sea una entidad, ya sea que se cree o no una tabla en BD,
//EL lenguaje JPQL lo seguira tomando como entidad haciendo los joins necesarios u otras operaciones para mantener esta abstraccion
//@Inheritance(strategy = InheritanceType.JOINED)
//Para la columna que distingue el tipo de instancia en SINGLE_TABLE
//@DiscriminatorColumn(name = "EMPLOYEE_TYPE")
public abstract class Employee {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public Employee(String name) {
        this.name = name;
    }
}
