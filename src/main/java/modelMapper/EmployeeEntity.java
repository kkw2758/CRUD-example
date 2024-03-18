package modelMapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeEntity {
    private String employeeId;
    private String employeeName;
    private String employeeAddr;
}
