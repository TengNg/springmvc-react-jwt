package com.ndt.controllers;

import com.ndt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@ControllerAdvice
public class PageAccountController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("/accounts")
	public String accounts(Model model) {
        model.addAttribute("accounts", this.userService.getAllUsers());
        return "accounts";
    }

	@PostMapping("/confirm")
    public String deleteProduct(@RequestParam String username, RedirectAttributes redirectAttributes) {
        userService.confirmSellerAccount(username);
        redirectAttributes.addFlashAttribute("message", "Seller account is confirmed");
        return "redirect:/accounts";
    }
}
