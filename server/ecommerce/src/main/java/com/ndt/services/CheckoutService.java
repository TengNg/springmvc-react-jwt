package com.ndt.services;

import com.ndt.pojo.Cart;
import com.ndt.pojo.PaymentTransaction;
import com.ndt.pojo.Product;
import com.ndt.pojo.User;
import com.ndt.repositories.CartRepository;
import com.ndt.repositories.PaymentTransactionRepository;
import com.ndt.repositories.ProductRepository;
import com.ndt.repositories.UserRepository;
import java.math.BigDecimal;
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
    @Autowired
    private PaymentTransactionRepository paymentTransactionRepo;

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

	public PaymentTransaction saveTransaction(Map<String, Object> request) {
		String username = (String) request.get("username");
		String cartId = (String) request.get("cartId");
		String method = (String) request.get("paymentMethod");
		BigDecimal amount = new BigDecimal((String) request.get("amount"));

		PaymentTransaction transaction = new PaymentTransaction();
		transaction.setUserId(this.userRepository.getUserByUsername(username));
		transaction.setCartId(this.cartRepository.getCart(cartId));
		transaction.setAmount(amount);
		transaction.setMethod(method);
		transaction.setTransactionDate(new Date());

		this.paymentTransactionRepo.saveTransaction(transaction);
		return transaction;
	}

	public List<PaymentTransaction> getTransactionsByUserId(String userId) {
		return this.paymentTransactionRepo.getTransactionsByUserId(userId);
	}	

	public PaymentTransaction getTransactionByCartId(String cartId) {
		return this.paymentTransactionRepo.getTransactionByCartId(cartId);
	}	
}
