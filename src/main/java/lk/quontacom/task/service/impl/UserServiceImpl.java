package lk.quontacom.task.service.impl;

import lk.quontacom.task.exception.ERSException;
import lk.quontacom.task.model.Repository.UserRepository;
import lk.quontacom.task.model.dto.request.UserReqDto;
import lk.quontacom.task.model.dto.response.UserRespDto;
import lk.quontacom.task.model.entity.User;
import lk.quontacom.task.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Test;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final  UserService userService;

    public UserServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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
        return userRepository.findByUser_id(id)
                .orElseThrow(() -> new ERSException(
                        HttpStatus.BAD_REQUEST,
                        "User does not exist for the id",
                        "User does not exist for the id: " + id));
    }

}
