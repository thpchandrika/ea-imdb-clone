package edu.miu.config;

import edu.miu.dto.UserSaveDto;
import edu.miu.enums.Role;
import edu.miu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataConfig implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if(!userService.checkUser("admin@admin.com")){
            UserSaveDto userDto = new UserSaveDto();
            userDto.setEmail("admin@admin.com");
            userDto.setPassword("admin@123");
            userDto.setRole(Role.ADMIN);
            userDto.setAddress1("1000N 4th St.");
            userDto.setCity("Fairfield");
            userDto.setState("IA");
            userDto.setCountry("USA");
            userDto.setZipCode("52557");
            userService.saveUser(userDto);
        }
    }
}
