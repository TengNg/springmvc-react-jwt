/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.controllers;

import com.ndt.pojo.User;
import com.ndt.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageUserController {
	@Autowired
	private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

	@PostMapping("/login")
    public String login(
			@RequestParam String username, 
			@RequestParam String password, 
			HttpServletRequest request
	) {
		User user = this.userService.getUserByUsername(username);
		boolean isValid = this.userService.authUser(user.getUsername(), password);
		if (isValid == true) {
			HttpSession session = request.getSession();
	        session.setAttribute("username", user.getUsername());
	        session.setAttribute("password", password);
			return "redirect:/accounts";
		}
		return "login";
    }
}