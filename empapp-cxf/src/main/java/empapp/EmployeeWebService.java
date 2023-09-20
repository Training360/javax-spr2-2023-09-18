package empapp;

import empapp.dto.EmployeeDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@WebService(serviceName = "EmployeeWebService", targetNamespace = "http://training360.com/services/empapp")
@Service
@AllArgsConstructor
public class EmployeeWebService {

    private EmployeeService employeeService;

    @WebMethod
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    public List<EmployeeDto> employees() {
        return employeeService.listEmployees();
    }
}
