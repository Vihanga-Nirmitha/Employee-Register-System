package lk.quontacom.task.service;

import lk.quontacom.task.exception.ERSException;
import lk.quontacom.task.model.dto.request.EmployeeReqDto;
import lk.quontacom.task.model.dto.request.UserReqDto;
import lk.quontacom.task.model.dto.response.EmployeeRespDto;
import lk.quontacom.task.model.dto.response.UserRespDto;
import lk.quontacom.task.model.entity.User;
import org.aspectj.weaver.ast.Test;

import java.util.List;

public interface UserService {
    void createUser(UserReqDto userReqDto) throws ERSException;
    List<UserReqDto> getAllUsers();
    UserRespDto getUserById(String userId) throws ERSException;
    UserRespDto deleteUserById(String userId) throws ERSException;
    UserRespDto editUserById(String userId , UserReqDto userReqDto) throws ERSException;
    User findById(String id) throws ERSException;
}
