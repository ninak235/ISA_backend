package com.ISA.ISAProject;


import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;


import java.util.Arrays;

@EnableAsync
@SpringBootApplication
public class IsaProjectApplication {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(IsaProjectApplication.class, args);
	}

	@Value("response")
	String queue;

	@Value("exchange2")
	String exchange;

	@Value("response")
	String routingkey;

	@Bean
	Queue queue() {
		return new Queue(queue, true);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		return connectionFactory;
	}

}
