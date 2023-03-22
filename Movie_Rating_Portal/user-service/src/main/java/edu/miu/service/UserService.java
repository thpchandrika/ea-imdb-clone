package edu.miu.service;

import edu.miu.dto.UserDto;
import edu.miu.dto.UserSaveDto;

public interface UserService {

    UserDto saveUser(UserSaveDto userSaveDto);
    Boolean checkUser(String email);
    void deleteUser(Long id);
}
