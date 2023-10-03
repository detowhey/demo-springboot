package com.henriquealmeida.democrud.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.henriquealmeida.democrud.domain.Category;
import com.henriquealmeida.democrud.domain.Order;
import com.henriquealmeida.democrud.domain.OrderItem;
import com.henriquealmeida.democrud.domain.Payment;
import com.henriquealmeida.democrud.domain.Product;
import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.domain.enu.OrderStatus;
import com.henriquealmeida.democrud.repositories.CategoryRepository;
import com.henriquealmeida.democrud.repositories.OrderItemRepository;
import com.henriquealmeida.democrud.repositories.OrderRepository;
import com.henriquealmeida.democrud.repositories.ProductRepository;
import com.henriquealmeida.democrud.repositories.UserRepository;

/*
*	class specifies for configuration, notation
*	configuration class to instantiate the database
*/
@Configuration
// test profile only
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

	}
}
