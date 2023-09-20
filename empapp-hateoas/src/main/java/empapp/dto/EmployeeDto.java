package empapp.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class EmployeeDto extends RepresentationModel<EmployeeDto> {

    private Long id;

    private String name;

    private List<AddressDto> addresses;
}
