package lk.quontacom.task.service;

import lk.quontacom.task.exception.ERSException;
import lk.quontacom.task.model.dto.request.EmployeeReqDto;
import lk.quontacom.task.model.dto.response.EmployeeRespDto;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeReqDto employeeReqDto) throws ERSException;
    List<EmployeeRespDto> getAllEmployees();
    EmployeeRespDto getEmployeeById(String employeeId) throws ERSException;
    EmployeeRespDto deleteEmployeeById(String employeeId) throws ERSException;
    EmployeeRespDto editEmployeeById(String employeeId , EmployeeReqDto employeeReqDto) throws ERSException;


}
