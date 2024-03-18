package modelMapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String employeeId;
    private String employeeName;
    private String employeeAddr;
}
