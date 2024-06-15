package lk.quontacom.task.ers.controller;

import jakarta.validation.Valid;
import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.dto.request.AuthReq;
import lk.quontacom.task.ers.model.dto.request.UserReqDto;
import lk.quontacom.task.ers.model.dto.response.AuthResp;
import lk.quontacom.task.ers.model.dto.response.UserRespDto;
import lk.quontacom.task.ers.service.UserService;
import lk.quontacom.task.ers.service.authentication.AuthService;
import lk.quontacom.task.ers.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping  ("${base-url.context}/v1/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    private  final AuthService authService;
@Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;

    this.authService = authService;
}
    @PostMapping
    public ResponseEntity<AuthResp> register(@RequestBody @Valid UserReqDto userReqDto) throws ERSException {
        log.info("Received request to create User");
        return new ResponseEntity<>(authService.register(userReqDto),HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResp> authenticate(@RequestBody AuthReq authReq) throws ERSException {
        return new ResponseEntity<>(authService.authentication(authReq), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserRespDto> getAllUsersById(@PathVariable("id") String userId) throws ERSException{
        log.info("Received request to get User"+ userId);
        userService.getUserById(userId);
        return new ResponseEntity<UserRespDto>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UserRespDto>> getAllUsers() throws ERSException{
        log.info("Received request to get all Users");
        userService.getAllUsers();
        return new ResponseEntity<List<UserRespDto>>(HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") String userId) throws ERSException{
        log.info("Received request to Delete User"+userId);
        userService.deleteUserById(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserRespDto> editEmployee(@PathVariable("id") String userId, @RequestBody @Valid  UserReqDto userReqDto) throws ERSException{
        log.info("Received request to Edit User" + userId);
        userService.editUserById(userId,userReqDto);
        return new ResponseEntity<UserRespDto>(HttpStatus.CREATED);
    }
}
