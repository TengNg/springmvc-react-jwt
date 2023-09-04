package com.ndt.services;

import com.ndt.pojo.Product;
import com.ndt.pojo.ProductReview;
import com.ndt.pojo.User;
import com.ndt.repositories.ProductRepository;
import com.ndt.repositories.ProductReviewRepository;
import com.ndt.repositories.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewService {
    @Autowired
	private ProductReviewRepository reviewRepository;
    @Autowired
	private UserRepository userRepository;
    @Autowired
	private ProductRepository productRepository;

	public List<ProductReview> getReviews(String productId) {
		return this.reviewRepository.getReviews(productId);
	}

    public ProductReview postReview(Map<String, String> params) {
		String productId = params.get("productId");
		String username = params.get("username");
		String reviewText = params.get("reviewText");
		// Integer rating = (Integer) params.get("rating");

		ProductReview productReview = new ProductReview();
		productReview.setReviewId(UUID.randomUUID().toString());
		productReview.setReviewDate(new Date());
		productReview.setReviewText(reviewText);

		User u = this.userRepository.getUserByUsername(username);
		productReview.setUserId(u);

		Product product = this.productRepository.getProductById(productId);
		productReview.setProductId(product);

		this.reviewRepository.postReview(productReview);
		return productReview;
    }
}

