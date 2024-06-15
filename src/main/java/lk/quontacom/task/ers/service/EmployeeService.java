package lk.quontacom.task.ers.service;

import lk.quontacom.task.ers.model.dto.request.EmployeeReqDto;
import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.dto.response.EmployeeRespDto;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeReqDto employeeReqDto) throws ERSException;
    List<EmployeeRespDto> getAllEmployees();
    EmployeeRespDto getEmployeeById(String employeeId) throws ERSException;
    void deleteEmployeeById(String employeeId) throws ERSException;
    EmployeeRespDto editEmployeeById(String employeeId , EmployeeReqDto employeeReqDto) throws ERSException;


}
