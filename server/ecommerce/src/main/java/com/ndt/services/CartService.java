package com.ndt.services;

import com.ndt.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	@Autowired
    private CartRepository cartRepository;

	public void delete(String cartId) {
		this.cartRepository.delete(cartId);
	}
}


