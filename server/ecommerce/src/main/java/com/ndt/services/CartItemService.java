package com.ndt.services;

import com.ndt.pojo.Cart;
import com.ndt.pojo.CartItem;
import com.ndt.pojo.Product;
import com.ndt.pojo.User;
import com.ndt.repositories.CartItemRepository;
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
public class CartItemService {
	@Autowired
    private CartItemRepository cartItemRepository;
	@Autowired
    private ProductRepository productRepository;
	@Autowired
    private CartRepository cartRepository;

	public CartItem addCartItem(Map<String, Object> request) {
		String cartId = (String) request.get("cartId");
		String productId = (String) request.get("id");
		Integer quantity = (Integer) request.get("quantity");

		Product product = this.productRepository.getProductById(productId);
		Cart cart = this.cartRepository.getCart(cartId);

		CartItem cartItem = new CartItem();
		cartItem.setCartId(cart);
		cartItem.setProductId(product);
		cartItem.setQuantity(quantity);

		this.cartItemRepository.addCartItem(cartItem);

		return cartItem;
	}

    public List<CartItem> getCartItems(String cartId) {
		return null;
    }
}

