package lk.quontacom.task.ers.service.impl;

import lk.quontacom.task.ers.model.Repository.RoleRepository;
import lk.quontacom.task.ers.model.entity.Role;
import lk.quontacom.task.ers.service.RoleService;
import lk.quontacom.task.ers.util.enums.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void feedUserRole() {
        if(!roleRepository.existsById("1")){
            roleRepository.save(new Role(1L, RoleType.ADMIN));
            log.info("Successfully feed admin role");
        }
        if(!roleRepository.existsById("2")){
            roleRepository.save(new Role(2L, RoleType.USER));
            log.info("Successfully feed USER role");
        }
    }
}
