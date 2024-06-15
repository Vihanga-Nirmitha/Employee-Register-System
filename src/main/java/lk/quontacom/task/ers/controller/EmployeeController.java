package lk.quontacom.task.ers.controller;

import jakarta.validation.Valid;
import lk.quontacom.task.ers.model.dto.request.EmployeeReqDto;
import lk.quontacom.task.ers.service.EmployeeService;
import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.dto.response.EmployeeRespDto;
import lk.quontacom.task.ers.service.authentication.AuthService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${base-url.context}/v1/api/employees")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    private final AuthService authService;
    @Autowired
    public EmployeeController(EmployeeService employeeService, AuthService authService) {
        this.employeeService = employeeService;
        this.authService = authService;
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

        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeRespDto>> getAllEmployees() throws ERSException{
        log.info("Received request to get all Employees");

        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") String employeeId ,@RequestBody String token) throws ERSException{
        log.info("Received request to Delete Employee: "+employeeId);

        employeeService.deleteEmployeeById(employeeId,authService.extractUserEmailFromToken(token));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeRespDto> editEmployee(@PathVariable("id") String employeeId, @RequestBody @Valid  EmployeeReqDto employeeReqDto) throws ERSException{
        log.info("Received request to Edit Employee" + employeeId);

        return new ResponseEntity<>(employeeService.editEmployeeById(employeeId,employeeReqDto),HttpStatus.CREATED);
    }

    @PostMapping("/{id}/uploadProfilePicture")
    public ResponseEntity<String> uploadProfilePic(@PathVariable("id") String employeeId,
                                                   @RequestParam("file") MultipartFile file) throws ERSException {
        log.info("Received request to uploadProfilePic" + employeeId);
        return new ResponseEntity<>(employeeService.uploadProfilePic(employeeId,file),HttpStatus.CREATED) ;
    }
    @GetMapping("/{id}/downloadProfilePicture")
    public ResponseEntity<byte[]> downloadProfilePic(@PathVariable("id") String employeeId) throws ERSException {
        return new ResponseEntity<>(employeeService.downloadProfilePic(employeeId),HttpStatus.OK);
    }
    @PostMapping("/report")
    public ResponseEntity<Void> getEmployeeReport() throws ERSException, JRException {
        log.info("Received request to get employee report");
        employeeService.getEmployeeReport();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
