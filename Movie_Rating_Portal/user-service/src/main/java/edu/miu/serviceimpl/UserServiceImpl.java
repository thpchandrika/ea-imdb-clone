package edu.miu.serviceimpl;

import edu.miu.dto.UserDto;
import edu.miu.dto.UserSaveDto;
import edu.miu.exception.UserAlreadyExistException;
import edu.miu.mapper.UserSaveMapper;
import edu.miu.repository.UserRepository;
import edu.miu.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {


    private UserSaveMapper userSaveMapper;
    private UserRepository userRepository;

    private AmqpTemplate amqpTemplate;
    private final Queue userQueue1;


    public UserServiceImpl(UserSaveMapper userSaveMapper, UserRepository userRepository, AmqpTemplate amqpTemplate, Queue userQueue1) {
        this.userSaveMapper = userSaveMapper;
        this.userRepository = userRepository;
        this.amqpTemplate = amqpTemplate;
        this.userQueue1 = userQueue1;
    }

    @Override
    public UserDto saveUser(UserSaveDto userSaveDto) {
        userRepository.findByEmail(userSaveDto.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistException("Email " + user.getEmail() + " already exist!");
        });
        return userSaveMapper.toDto(userRepository.save(userSaveMapper.toEntity(userSaveDto)));
    }

    @Override
    public Boolean checkUser(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void deleteUser(Long id) {
        amqpTemplate.convertAndSend(this.userQueue1.getName(), id);
    }
}
