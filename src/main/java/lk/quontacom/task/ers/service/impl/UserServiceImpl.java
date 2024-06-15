package lk.quontacom.task.ers.service.impl;

import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.Repository.UserRepository;
import lk.quontacom.task.ers.model.dto.response.EmployeeRespDto;
import lk.quontacom.task.ers.model.dto.response.UserRespDto;
import lk.quontacom.task.ers.model.entity.Employee;
import lk.quontacom.task.ers.model.entity.User;
import lk.quontacom.task.ers.model.dto.request.UserReqDto;
import lk.quontacom.task.ers.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    public void createUser(UserReqDto userReqDto) throws ERSException {

    }

    @Override
    public List<UserReqDto> getAllUsers() {
        return null;
    }



    @Override
    public UserRespDto getUserById(String userId) throws ERSException {
        return null;
    }

    @Override
    public UserRespDto deleteUserById(String userId) throws ERSException {
        return null;
    }

    @Override
    public UserRespDto editUserById(String userId, UserReqDto userReqDto) throws ERSException {
        return null;
    }

    @Override
    public User findById(String id) throws ERSException {
        log.debug("User findById method started: {}");
        return userRepository.findById(id)
                .orElseThrow(() -> new ERSException(
                        HttpStatus.BAD_REQUEST,
                        "User does not exist for the id",
                        "User does not exist for the id: " + id));
    }

}
