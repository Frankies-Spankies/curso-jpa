package frank.cursos.handsonjpa.bootstrap;

import frank.cursos.handsonjpa.entities.FullTimeEmployee;
import frank.cursos.handsonjpa.entities.PartTimeEmployee;
import frank.cursos.handsonjpa.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@Order(2)
public class BootstrapEmployees implements CommandLineRunner {

    @Autowired
    EmployeeRepository repository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("########################################################################################################################################################");
        System.out.println("##############################################################################   Empoyees   ############################################################");
        repository.insert(new FullTimeEmployee("Frank", new BigDecimal("30000")));
        repository.insert(new PartTimeEmployee("Panki", new BigDecimal("200")));

        log.info("FullTimeEmployee {}", repository.retrieveFullTimeEmployee());
        log.info("PartTimeEmployee {}", repository.retrievePartTimeEmployee());
    }
}
