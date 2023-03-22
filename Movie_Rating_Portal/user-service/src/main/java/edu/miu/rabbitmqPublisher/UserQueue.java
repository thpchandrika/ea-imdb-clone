package edu.miu.rabbitmqPublisher;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserQueue {

    @Bean
    public Queue userQueue1() {
        return new Queue("userQueue-1", true);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("user-fanout-exchange");
    }

    @Bean
    Binding userQueue1Binding(Queue userQueue1, FanoutExchange helloFanoutExchange) {
        return BindingBuilder.bind(userQueue1).to(helloFanoutExchange);
    }

}
