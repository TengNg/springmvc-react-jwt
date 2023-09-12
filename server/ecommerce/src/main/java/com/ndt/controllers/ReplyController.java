package com.ndt.controllers;

import com.ndt.pojo.Reply;
import com.ndt.services.ReplyService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(
		origins = {
			"http://localhost:5173",
			"http://localhost:5174",
			"http://localhost:3000",
			"http://localhost:3001",
			"http://localhost:8080"

		},
		allowCredentials = "true",
		maxAge = 3600,
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST,
			RequestMethod.DELETE, RequestMethod.PUT,
			RequestMethod.PATCH, RequestMethod.OPTIONS,
			RequestMethod.HEAD, RequestMethod.TRACE}
)
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@GetMapping("/replies/{reviewId}")
	public ResponseEntity<Map<String, Object>> replies(@PathVariable String reviewId) {
		List<Reply> replies = this.replyService.getReplies(reviewId);

		Map<String, Object> data = new HashMap<>();
		data.put("replies", replies(reviewId));

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping("/replies/post")
	public ResponseEntity<?> post(@RequestBody Map<String, Object> params) {
		Reply reply = this.replyService.postReply(params);

		Map<String, Object> data = new HashMap<>();
		data.put("msg", "review is successfully posted");
		data.put("reply", reply);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}


