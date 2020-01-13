package com.henriquealmeida.democrud.config;


import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.henriquealmeida.democrud.entities.Order;
import com.henriquealmeida.democrud.entities.User;
import com.henriquealmeida.democrud.repositories.OrderRepository;
import com.henriquealmeida.democrud.repositories.UserRepository;

/*
*	class specifies for configuration, notation
*	configuration class to instantiate the database
*/
@Configuration
// test profile only
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order (null, Instant.parse("2019-06-20T19:53:07Z"),user1);
		Order o2 = new Order (null, Instant.parse("2019-07-21T03:53:22Z"),user2);
		Order o3 = new Order (null, Instant.parse("2019-07-22T15:21:22Z"),user1);
		
		userRepository.saveAll(Arrays.asList(user1,user2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	}
}
