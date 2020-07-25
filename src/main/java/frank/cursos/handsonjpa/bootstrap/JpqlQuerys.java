package frank.cursos.handsonjpa.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(3)
public class JpqlQuerys implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("########################################################################################################################################################");
        System.out.println("###########################################################################   JPQL Querys   ############################################################");
    }
}
