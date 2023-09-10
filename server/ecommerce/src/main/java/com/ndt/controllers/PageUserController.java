/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.controllers;

import com.ndt.services.UserService;
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
    public String login(@RequestParam String username, @RequestParam String password) {
		boolean user = this.userService.authUser(username, password);
		if (user == true) {
			return "redirect:/accounts";
		}
		return "login";
    }
}