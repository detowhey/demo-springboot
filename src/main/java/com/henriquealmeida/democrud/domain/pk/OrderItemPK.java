package com.henriquealmeida.democrud.domain.pk;

import com.henriquealmeida.democrud.domain.Order;
import com.henriquealmeida.democrud.domain.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemPK{

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
