package com.ndt.services;

import com.ndt.pojo.Product;
import com.ndt.pojo.ProductReview;
import com.ndt.pojo.Reply;
import com.ndt.pojo.User;
import com.ndt.repositories.ProductRepository;
import com.ndt.repositories.ProductReviewRepository;
import com.ndt.repositories.ReplyRepository;
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
public class ReplyService {
	@Autowired
	private ReplyRepository replyRepository;
    @Autowired
	private UserRepository userRepository;
    @Autowired
	private ProductReviewRepository reviewRepository;

	public List<Reply> getReplies(String reviewId) {
		return this.replyRepository.getReplies(reviewId);
	}	

    public Reply postReply(Map<String, Object> params) {
		String reviewId = (String) params.get("reviewId");
		String username = (String) params.get("username");
		String responseText = (String) params.get("responseText");

		Reply reply = new Reply();
		reply.setReplyId(UUID.randomUUID().toString());
		reply.setCreatedAt(new Date());

		User user = this.userRepository.getUserByUsername(username);
		ProductReview review = this.reviewRepository.getProductReviewById(reviewId);

		reply.setUserId(user);
		reply.setReviewId(review);
		reply.setResponseText(responseText);

		this.replyRepository.postReply(reply);
		
		return reply;
    }
}


