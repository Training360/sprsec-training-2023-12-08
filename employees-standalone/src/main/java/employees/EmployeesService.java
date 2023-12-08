package employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeesRepository repository;

    public List<EmployeeModel> listEmployees() {
        return repository.findAllResources();
    }

    public EmployeeModel findEmployeeById(long id) {
        return toDto(repository.findById(id).orElseThrow(notFountException(id)));
    }

    public EmployeeModel createEmployee(EmployeeModel command) {
        var employee = new Employee(command.getName());
        repository.save(employee);
        return toDto(employee);
    }

    @Transactional
    public EmployeeModel updateEmployee(long id, EmployeeModel command) {
        var employee = repository.findById(id).orElseThrow(notFountException(id));
        employee.setName(command.getName());
        return toDto(employee);
    }

    public void deleteEmployee(long id) {
        repository.deleteById(id);
    }

    private EmployeeModel toDto(Employee employee) {
        return new EmployeeModel(employee.getId(), employee.getName());
    }

    private Supplier<EmployeeNotFoundException> notFountException(long id) {
        return () -> new EmployeeNotFoundException("Employee not found with id: %d".formatted(id));
    }

}
