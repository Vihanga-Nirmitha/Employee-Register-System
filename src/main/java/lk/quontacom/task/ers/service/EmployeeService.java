package lk.quontacom.task.ers.service;

import lk.quontacom.task.ers.model.dto.report.EmployeeReport;
import lk.quontacom.task.ers.model.dto.request.EmployeeReqDto;
import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.dto.request.UserReqDto;
import lk.quontacom.task.ers.model.dto.response.EmployeeRespDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeReqDto employeeReqDto) throws ERSException;
    List<EmployeeRespDto> getAllEmployees();
    EmployeeRespDto getEmployeeById(String employeeId) throws ERSException;
    void deleteEmployeeById(String employeeId,String email) throws ERSException;
    EmployeeRespDto editEmployeeById(String employeeId , EmployeeReqDto employeeReqDto) throws ERSException;
    Void getEmployeeReport() throws ERSException, JRException;
    String uploadProfilePic(String id, MultipartFile file) throws ERSException;
    byte[] downloadProfilePic(String id) throws ERSException;
}
