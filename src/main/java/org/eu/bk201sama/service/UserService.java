package org.eu.bk201sama.service;


import org.eu.bk201sama.constant.ProcessEnum;
import org.eu.bk201sama.entity.User;
import org.eu.bk201sama.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public User getAndInitUserById(String fromUserId) {
        Optional<User> userOptional = userRepository.findById(fromUserId);
        if(!userOptional.isPresent()){
            User init = User.builder()
                    .id(fromUserId)
                    .process(ProcessEnum.MENU)
                    .menuId(1)
                    .build();
        }
        return userOptional.get();
    }
}
