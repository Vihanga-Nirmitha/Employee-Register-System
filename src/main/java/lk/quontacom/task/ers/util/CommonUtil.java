package lk.quontacom.task.ers.util;

import io.micrometer.common.util.StringUtils;
import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.dto.report.EmployeeReport;
import lk.quontacom.task.ers.model.entity.Role;
import lk.quontacom.task.ers.util.enums.Gender;
import lk.quontacom.task.ers.util.enums.RoleType;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CommonUtil {
    public LocalDateTime convertStringToDateTime(String providedStringDateTime) {
        log.debug("convertStringToDateTime method started. providedStringDateTime: {}", providedStringDateTime);
        return StringUtils.isBlank(providedStringDateTime) ? null : LocalDateTime.parse(providedStringDateTime, Constant.DATE_TIME_FORMATTER);
    }

    public String convertDateTimeToString(LocalDateTime localDateTime) {
        log.debug("convertDateTimeToString method started. localDateTime: {}", localDateTime);
        return localDateTime.format(Constant.DATE_TIME_FORMATTER);
    }
    public Gender convertStringToGender(String genderStr){
        log.debug("convertStringToGender method started. Gender :", genderStr);
        Gender gender = (genderStr.equals("FEMALE"))? Gender.FEMALE: Gender.MALE;

        return gender;
    }
    public String convertgenderToString(Gender gender){
        log.debug("convertGenderToString method started. Gender :", gender);
        String genderStr = (Gender.MALE == gender)? "MALE": "FEMALE";
        return genderStr;
    }
    public RoleType convertStringToRole(String roleStr){
        log.debug("convertStringToRole method started. Role :", roleStr);
        RoleType role = (roleStr.equals("ADMIN"))? RoleType.ADMIN: RoleType.USER;

        return role;
    }
    public String convertRoleToString(RoleType role){
        log.debug("convertRoleToString method started. Role :", role);
        String roleStr = (RoleType.USER == role)? "USER": "ADMIN";
        return roleStr;
    }

    public void employeeReportGenerate(List<EmployeeReport> employeeReportList) throws JRException {


        try {
            String reportPath = "src/main/resources/Employee Details Report.jasper";
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeReportList);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "EmployeeReport.pdf");
            log.info("Report created successfully");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}
