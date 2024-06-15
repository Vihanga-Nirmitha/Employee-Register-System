package lk.quontacom.task.ers.service.authentication;

import lk.quontacom.task.ers.model.Repository.RoleRepository;
import lk.quontacom.task.ers.model.Repository.UserRepository;
import lk.quontacom.task.ers.model.dto.request.AuthReq;
import lk.quontacom.task.ers.model.dto.request.UserReqDto;
import lk.quontacom.task.ers.model.dto.response.AuthResp;
import lk.quontacom.task.ers.model.entity.User;
import lk.quontacom.task.ers.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final AuthenticationManager authenticationManager;
@Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService, UserRepository userRepository, RoleRepository roleRepository, CommonUtil commonUtil, AuthenticationManager authenticationManager) {
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
        this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.commonUtil = commonUtil;

    this.authenticationManager = authenticationManager;
    }

    public AuthResp register(UserReqDto userReqDto) {
        User user = new User();
        user.setEmail(userReqDto.getEmail());
        user.setFirst_name(userReqDto.getFirst_name());
        user.setLast_name(userReqDto.getLast_name());
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        user.setRole(roleRepository.findByRole(commonUtil.convertStringToRole(userReqDto.getRole())));
        userRepository.save(user);
        String jwtToken = jwtService.generateToken((UserDetails) user);
        return new AuthResp(jwtToken);
    }


    public AuthResp authentication(AuthReq authReq){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authReq.getEmail(),
                authReq.getPassword()
        ));
        var user= userRepository.findByEmail(authReq.getEmail());
        String token = jwtService.generateToken((UserDetails) user);
        return new AuthResp(token);
    }
}
