package lk.quontacom.task.ers.util;

import io.micrometer.common.util.StringUtils;
import lk.quontacom.task.ers.model.entity.Role;
import lk.quontacom.task.ers.util.enums.Gender;
import lk.quontacom.task.ers.util.enums.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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


}
