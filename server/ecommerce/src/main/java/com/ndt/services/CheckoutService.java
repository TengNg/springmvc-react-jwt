package com.ndt.services;

import com.ndt.pojo.Cart;
import com.ndt.pojo.Product;
import com.ndt.pojo.User;
import com.ndt.repositories.CartRepository;
import com.ndt.repositories.ProductRepository;
import com.ndt.repositories.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    public Cart proceedCheckout(Map<String, Object> params) {
		// cart cartItems paymentTransaction shipppingProcess
		// get user info from api and fill checkout form
		// update user info in checkout form

		String username = (String) params.get("username");
		String paymentMethod = (String) params.get("paymentMethod");
		User user = this.userRepository.getUserByUsername(username);

		Cart cart = new Cart();
		cart.setCartId(UUID.randomUUID().toString());
		cart.setCreatedAt(new Date());
		cart.setPaymentMethod(paymentMethod);
		cart.setUserId(user);

		this.cartRepository.addCart(cart);

		return cart;
    }

	public List<Cart> getCartsByUserId(String userId) {
		return this.cartRepository.getCartsBydUserId(userId);
	}
}
