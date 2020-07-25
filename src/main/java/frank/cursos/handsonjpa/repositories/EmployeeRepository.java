package frank.cursos.handsonjpa.repositories;

import frank.cursos.handsonjpa.entities.Employee;
import frank.cursos.handsonjpa.entities.FullTimeEmployee;
import frank.cursos.handsonjpa.entities.PartTimeEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    EntityManager manager;

    public void insert(Employee employee){
        manager.persist(employee);
    }

    public List<FullTimeEmployee> retrieveFullTimeEmployee(){
        Query query = manager.createQuery("SELECT e FROM FullTimeEmployee e", FullTimeEmployee.class);
        List<FullTimeEmployee> employees = query.getResultList();
        return employees;
    }

    public List<PartTimeEmployee> retrievePartTimeEmployee() {
        Query query = manager.createQuery("SELECT e FROM PartTimeEmployee e", PartTimeEmployee.class);
        List<PartTimeEmployee> employees = query.getResultList();
        return employees;
    }
}
