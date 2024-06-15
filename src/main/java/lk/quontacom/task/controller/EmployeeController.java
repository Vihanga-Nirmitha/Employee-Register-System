package lk.quontacom.task.controller;

import jakarta.validation.Valid;
import lk.quontacom.task.exception.ERSException;
import lk.quontacom.task.model.dto.request.EmployeeReqDto;
import lk.quontacom.task.model.dto.response.EmployeeRespDto;
import lk.quontacom.task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${base-url.context}/v1/api/Employees")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid EmployeeReqDto employeeReqDto) throws ERSException{
        log.info("Received request to create Employee");
        employeeService.createEmployee(employeeReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeRespDto> getEmployeeById(@PathVariable("id") String employeeId) throws ERSException{
        log.info("Received request to get Employee"+ employeeId);
        employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<EmployeeRespDto>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeRespDto>> getAllEmployees() throws ERSException{
        log.info("Received request to get all Employees");
        employeeService.getAllEmployees();
        return new ResponseEntity<List<EmployeeRespDto>>(HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") String employeeId) throws ERSException{
        log.info("Received request to Delete Employee"+employeeId);
        employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeRespDto> editEmployee(@PathVariable("id") String employeeId, @RequestBody @Valid  EmployeeReqDto employeeReqDto) throws ERSException{
        log.info("Received request to Edit Employee" + employeeId);
        employeeService.editEmployeeById(employeeId,employeeReqDto);
        return new ResponseEntity<EmployeeRespDto>(HttpStatus.CREATED);
    }


}
