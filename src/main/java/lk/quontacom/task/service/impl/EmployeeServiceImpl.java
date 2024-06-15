package lk.quontacom.task.service.impl;

import lk.quontacom.task.exception.ERSException;
import lk.quontacom.task.model.Repository.EmployeeRepository;
import lk.quontacom.task.model.Repository.UserRepository;
import lk.quontacom.task.model.dto.request.EmployeeReqDto;
import lk.quontacom.task.model.dto.response.EmployeeRespDto;
import lk.quontacom.task.model.entity.Employee;
import lk.quontacom.task.model.entity.User;
import lk.quontacom.task.service.EmployeeService;
import lk.quontacom.task.service.UserService;
import lk.quontacom.task.util.CommonUtil;
import lk.quontacom.task.util.enums.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableScheduling
public class EmployeeServiceImpl implements EmployeeService {

        private final EmployeeRepository employeeRepository;
        private final EmployeeService employeeService;

        private final UserRepository userRepository;
        private  final UserService userService;
        private  final  CommonUtil commonUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeService employeeService, UserRepository userRepository, UserService userService, CommonUtil commonUtil) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.commonUtil = commonUtil;
    }

    @Override
    public void createEmployee(EmployeeReqDto employeeReqDto) throws ERSException {
        log.debug("createEmployee method started");
        if(userRepository.existsByUser_id(employeeReqDto.getUser_id())){
            User  user  = userService.findById(employeeReqDto.getUser_id());

            Employee employee = new Employee();
            employee.setFirst_name(employeeReqDto.getFirst_name());
            employee.setLast_name(employeeReqDto.getLast_name());
            employee.setGender(commonUtil.convertStringToGender(employeeReqDto.getGender()));
            employee.setBirthday(commonUtil.convertStringToDateTime(employeeReqDto.getBirthday()));
            employee.setHired_date(commonUtil.convertStringToDateTime(employeeReqDto.getHired_date()));
            employee.setUser_id(user);
            updateCurrentAgeInDays(employee);
            log.info("Successfully created Employee");


        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "User does not exist with the user id", "User does not exist with the user id:" +employeeReqDto.getUser_id());
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
        if(employeeRepository.existsByEmployee_id(employeeId)){
            Employee employee = employeeRepository.findByEmployee_id(employeeId);
            EmployeeRespDto employeeRespDto = employeeToRespDto(employee);
            log.info("Successfully fetched the Employee");
            return employeeRespDto;
        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "Employee does not exist with the user id", "Employee does not exist with the user id:" +employeeId);
        }

    }

    @Override
    public EmployeeRespDto deleteEmployeeById(String employeeId) throws ERSException {
        log.debug("deleteEmployeeById method started");
        if(employeeRepository.existsByEmployee_id(employeeId)){
            Employee employee = employeeRepository.findByEmployee_id(employeeId);
            employee.setDeleted(true);
            EmployeeRespDto employeeRespDto = employeeToRespDto(employee);
            log.info("Successfully deleted the Employee");
            return employeeRespDto;
        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "Employee does not exist with the user id", "Employee does not exist with the user id:" +employeeId);
        }

    }

    @Override
    public EmployeeRespDto editEmployeeById(String employeeId, EmployeeReqDto employeeReqDto) throws ERSException {
        log.debug("editEmployee method started");
        if(userRepository.existsByUser_id(employeeReqDto.getUser_id())){
            User  user  = userService.findById(employeeReqDto.getUser_id());
            if(employeeRepository.existsByEmployee_id(employeeId)){
                Employee employee = employeeRepository.findByEmployee_id(employeeId);
                employee.setFirst_name(employeeReqDto.getFirst_name());
                employee.setLast_name(employeeReqDto.getLast_name());
                employee.setGender(commonUtil.convertStringToGender(employeeReqDto.getGender()));
                employee.setBirthday(commonUtil.convertStringToDateTime(employeeReqDto.getBirthday()));
                employee.setHired_date(commonUtil.convertStringToDateTime(employeeReqDto.getHired_date()));
                employee.setUser_id(user);
                updateCurrentAgeInDays(employee);
                log.info("Successfully updated Employee",employeeId);
            }else {
                throw new ERSException(HttpStatus.BAD_REQUEST,
                        "Employee does not exist with the employee id", "Employee does not exist with the user id:" +employeeId);
            }

        }else {
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "User does not exist with the user id", "User does not exist with the user id:" +employeeReqDto.getUser_id());
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
        return new EmployeeRespDto(String.valueOf(employee.getEmployee_id()),employee.getFirst_name(),employee.getLast_name()
               ,commonUtil.convertgenderToString(employee.getGender()), commonUtil.convertDateTimeToString(employee.getBirthday()),commonUtil.convertDateTimeToString(employee.getHired_date()),
                String.valueOf(employee.getCurrent_age_in_days()),String.valueOf(employee.getUser_id().getUser_id()));

    }


}
