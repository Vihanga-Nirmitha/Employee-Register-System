package lk.quontacom.task.ers.service.authentication;

import lk.quontacom.task.ers.exception.ERSException;
import lk.quontacom.task.ers.model.Repository.RoleRepository;
import lk.quontacom.task.ers.model.Repository.UserRepository;
import lk.quontacom.task.ers.model.dto.request.AuthReq;
import lk.quontacom.task.ers.model.dto.request.UserReqDto;
import lk.quontacom.task.ers.model.dto.response.AuthResp;
import lk.quontacom.task.ers.model.entity.User;
import lk.quontacom.task.ers.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
private final CommonUtil commonUtil;

@Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService, UserRepository userRepository, RoleRepository roleRepository, CommonUtil commonUtil) {
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;

    this.commonUtil = commonUtil;
}

    public AuthResp register(UserReqDto userReqDto) throws ERSException {
    if(userRepository.existsByEmail(userReqDto.getEmail())){
        throw new ERSException(HttpStatus.BAD_REQUEST,
                "Email already Exist", "Email already Exist Email :" + userReqDto.getEmail());
    }else {
        User user = new User();
        user.setEmail(userReqDto.getEmail());
        user.setFirst_name(userReqDto.getFirst_name());
        user.setLast_name(userReqDto.getLast_name());
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        user.setRole(roleRepository.findByRole(commonUtil.convertStringToRole(userReqDto.getRole())));
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthResp(jwtToken);
    }


    }


    public AuthResp authentication(AuthReq authReq) throws ERSException {
        var user = userRepository.findByEmail(authReq.getEmail());
        if(user==null){
            throw new ERSException(HttpStatus.BAD_REQUEST,
                    "User Email does not exist", "User Email does not exist Email :" + authReq.getEmail());
        }else {
            if (!passwordEncoder.matches(authReq.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid password");
            }
            String token = jwtService.generateToken(user);
            return new AuthResp(token);
        }


    }
}
