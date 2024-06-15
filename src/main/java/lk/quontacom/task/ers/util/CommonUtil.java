package lk.quontacom.task.ers.util;

import io.micrometer.common.util.StringUtils;
import lk.quontacom.task.ers.util.enums.Gender;
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


}
