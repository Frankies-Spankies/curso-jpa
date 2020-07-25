package frank.cursos.handsonjpa.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class PartTimeEmployee extends Employee {

    private BigDecimal hourlyWage;

    @Builder
    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }
}
