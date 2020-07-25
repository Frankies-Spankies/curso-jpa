package frank.cursos.handsonjpa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class FullTimeEmployee extends Employee {

    public BigDecimal salary;

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }
}
