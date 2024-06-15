package lk.quontacom.task.ers.service;

import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.dto.request.UserReqDto;
import lk.quontacom.task.ers.model.dto.response.UserRespDto;
import lk.quontacom.task.ers.model.entity.User;

import java.util.List;

public interface UserService {
    void createUser(UserReqDto userReqDto) throws ERSException;
    List<UserReqDto> getAllUsers();
    UserRespDto getUserById(String userId) throws ERSException;
    UserRespDto deleteUserById(String userId) throws ERSException;
    UserRespDto editUserById(String userId , UserReqDto userReqDto) throws ERSException;
    User findById(String id) throws ERSException;
}
