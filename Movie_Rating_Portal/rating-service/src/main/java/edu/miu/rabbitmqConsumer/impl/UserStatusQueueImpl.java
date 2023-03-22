package edu.miu.rabbitmqConsumer.impl;

import edu.miu.rabbitmqConsumer.UserStatusQueue;
import edu.miu.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserStatusQueueImpl implements UserStatusQueue {

    private ReviewRepository reviewRepository;

    @Override
    @RabbitListener(queues = {"userQueue-1"})
    public void updateUserStatus(Long id) {
        reviewRepository.updateDeletedUser(Boolean.TRUE, id);
    }
}
