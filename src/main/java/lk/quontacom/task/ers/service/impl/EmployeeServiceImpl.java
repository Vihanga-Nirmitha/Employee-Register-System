package lk.quontacom.task.ers.service.impl;

import lk.quontacom.task.ers.model.Repository.EmployeeRepository;
import lk.quontacom.task.ers.model.Repository.UserRepository;
import lk.quontacom.task.ers.model.dto.report.EmployeeReport;
import lk.quontacom.task.ers.model.dto.request.EmployeeReqDto;
import lk.quontacom.task.ers.util.CommonUtil;
import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.dto.response.EmployeeRespDto;
import lk.quontacom.task.ers.model.entity.Employee;
import lk.quontacom.task.ers.model.entity.User;
import lk.quontacom.task.ers.service.EmployeeService;
import lk.quontacom.task.ers.service.UserService;
import lk.quontacom.task.ers.util.enums.RoleType;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@EnableScheduling
public class EmployeeServiceImpl implements EmployeeService {
@Value("${file.upload-dir}")
    private  String uploadDir;
        private final EmployeeRepository employeeRepository;
        private final UserRepository userRepository;
        private  final UserService userService;
        private  final CommonUtil commonUtil;
@Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository, UserService userService, CommonUtil commonUtil) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.commonUtil = commonUtil;
    }

    @Override
    public void createEmployee(EmployeeReqDto employeeReqDto) throws ERSException {
        log.debug("createEmployee method started");
        if(userRepository.existsById(employeeReqDto.getUser())){
            User  user  = userService.findById(employeeReqDto.getUser());

            Employee employee = new Employee();
            employee.setFirst_name(employeeReqDto.getFirstName());
            employee.setLast_name(employeeReqDto.getLastName());
            employee.setGender(commonUtil.convertStringToGender(employeeReqDto.getGender()));
            employee.setBirthday(commonUtil.convertStringToDateTime(employeeReqDto.getBirthday()));
            employee.setHired_date(commonUtil.convertStringToDateTime(employeeReqDto.getHiredDate()));
            employee.setUser(user);
            employeeRepository.save(employee);
            updateCurrentAgeInDays(employee);
            log.info("Successfully created Employee");


        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "User does not exist with the user id", "User does not exist with the user id:" +employeeReqDto.getUser());
        }
    }

    @Override
    public List<EmployeeRespDto> getAllEmployees() {
        log.debug("getAllEmployees method started");
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeRespDto> employeeRespDtoList = employees.stream()
                .map(this::employeeToRespDto).toList();
        log.info("Successfully fetched employee list of size : ",employeeRespDtoList.size());
        return employeeRespDtoList;
    }

    @Override
    public EmployeeRespDto getEmployeeById(String employeeId) throws ERSException {
        log.debug("getEmployeeById method started");
        if(employeeRepository.existsById(employeeId)){
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            EmployeeRespDto employeeRespDto = employeeToRespDto(employee.get());
            log.info("Successfully fetched the Employee");
            return employeeRespDto;
        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "Employee does not exist with the user id", "Employee does not exist with the user id:" +employeeId);
        }

    }

    @Override
    public void deleteEmployeeById(String employeeId,String email) throws ERSException {
        log.debug("deleteEmployeeById method started");
        if(userRepository.findByEmail(email).getRole().getRole() == RoleType.ADMIN){
            if(employeeRepository.existsById(employeeId)){
                Optional<Employee> employee = employeeRepository.findById(employeeId);
                employee.get().setDeleted(true);
                employeeRepository.save(employee.get());
                log.info("Successfully deleted the Employee");


            }else {
                throw new ERSException(HttpStatus.BAD_REQUEST,
                        "Employee does not exist with the user id", "Employee does not exist with the user id:" +employeeId);
            }
        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "Only admin can delete a user", "Only admin can delete a user ");
        }


    }

    @Override
    public EmployeeRespDto editEmployeeById(String employeeId, EmployeeReqDto employeeReqDto) throws ERSException {
        log.debug("editEmployee method started");
        if(userRepository.existsById(employeeReqDto.getUser())){
            User  user  = userService.findById(employeeReqDto.getUser());
            if(employeeRepository.existsById(employeeId)){
                Optional<Employee> employee = employeeRepository.findById(employeeId);
                employee.get().setFirst_name(employeeReqDto.getFirstName());
                employee.get().setLast_name(employeeReqDto.getLastName());
                employee.get().setGender(commonUtil.convertStringToGender(employeeReqDto.getGender()));
                employee.get().setBirthday(commonUtil.convertStringToDateTime(employeeReqDto.getBirthday()));
                employee.get().setHired_date(commonUtil.convertStringToDateTime(employeeReqDto.getHiredDate()));
                employee.get().setUser(user);
                updateCurrentAgeInDays(employee.get());
                employeeRepository.save(employee.get());

                log.info("Successfully updated Employee",employeeId);
            }else {
                throw new ERSException(HttpStatus.BAD_REQUEST,
                        "Employee does not exist with the employee id", "Employee does not exist with the user id:" +employeeId);
            }

        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "User does not exist with the user id", "User does not exist with the user id:" +employeeReqDto.getUser());
        }
        return null;
    }
    @Scheduled(cron = "0 0 0 * * ?")
    private void getEmployeeListForAgeUpdate(){
        log.debug("getEmployeeListForAgeUpdate method started");
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee: employees) {updateCurrentAgeInDays(employee);}
        log.info("Successfully updated employees Age : ",employees.size());


    }
    private void updateCurrentAgeInDays(Employee employee){

        employee.setCurrent_age_in_days(ChronoUnit.DAYS.between(employee.getBirthday().toLocalDate(), LocalDate.now()));

    }
    private EmployeeRespDto employeeToRespDto(Employee employee) {

        return new EmployeeRespDto(String.valueOf(employee.getId()),employee.getFirst_name(),employee.getLast_name()
               ,commonUtil.convertgenderToString(employee.getGender()), commonUtil.convertDateTimeToString(employee.getBirthday()),commonUtil.convertDateTimeToString(employee.getHired_date()),
                String.valueOf(employee.getCurrent_age_in_days()),String.valueOf(employee.getUser().getId()));

    }
    @Override
    public String uploadProfilePic(String id, MultipartFile file) throws ERSException {
        if(employeeRepository.existsById(id)){
            try {

                Path uploadPath = Paths.get(this.uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String filename = id;
                Path filePath = uploadPath.resolve(filename);
                if(!Files.exists(filePath)){
                    Files.copy(file.getInputStream(), filePath);
                    log.info("Successfully uploaded employees profile picture : ",id);
                }else {
                    Path temp = filePath;
                    Files.delete(temp);
                    Files.copy(file.getInputStream(),filePath);
                    log.info("Successfully replaced employees profile picture : ",id);

                }

                return filename;
            } catch (IOException ex) {
                throw new RuntimeException("Failed to store file. Error: " + ex.getMessage());
            }
        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "Employee does not exist with the user id", "Employee does not exist with the user id:" +id);
        }

    }


    @Override
    public byte[] downloadProfilePic(String id) throws ERSException {
        Path filePath = Paths.get(this.uploadDir).resolve(id);
        if(employeeRepository.existsById(id)){
        try {
            byte[] fileContent = Files.readAllBytes(filePath);
            String mimeType = Files.probeContentType(filePath);

            return fileContent;
        } catch (IOException ex) {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "Profile picture does not exist with the user id", "Profile picture  does not exist with the user id:" +id);
        }

        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "Employee does not exist with the user id", "Employee does not exist with the user id:" +id);
        }
    }
    @Override
    public Void getEmployeeReport() throws ERSException, JRException {
        log.debug("getEmployeeReport method started");
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeReport> employeeReportlist = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeReport employeeReport = convertEmployeeToEmployeeReport(employee);
            employeeReportlist.add(employeeReport);
        }
        log.info("Successfully fetched employee list of size : ",employeeReportlist.size());
        commonUtil.employeeReportGenerate(employeeReportlist);

        return null;
    }
    private EmployeeReport convertEmployeeToEmployeeReport(Employee employee) throws ERSException {
        EmployeeReport employeeReport = new EmployeeReport(
                Math.toIntExact((employee.getId())),
                (employee.getFirst_name()+" "+employee.getLast_name()),
                (int)employee.getCurrent_age_in_days(),
                String.valueOf(commonUtil.convertgenderToString(employee.getGender())),
                Date.valueOf(employee.getHired_date().toLocalDate())
        );
        return employeeReport;

    }

}
