package empapp;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {

    @Bean
    public Endpoint employeeEndpoint(Bus bus, EmployeeWebService service) {
        var endpoint = new EndpointImpl(bus, service);
        endpoint.publish("/employees");
        return endpoint;
    }
}
