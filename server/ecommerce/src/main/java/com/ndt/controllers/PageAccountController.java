package com.ndt.controllers;

import com.ndt.services.CategoryService;
import com.ndt.services.ProductService;
import com.ndt.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@ControllerAdvice
public class PageAccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService cateService;
    
    @RequestMapping("/accounts")
	public String accounts(Model model) {
        model.addAttribute("accounts", this.userService.getAllUsers());
        return "accounts";
    }

	@PostMapping("/confirm")
    public String deleteProduct(@RequestParam String username, RedirectAttributes redirectAttributes) {
        userService.confirmSellerAccount(username);
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully");
        return "redirect:/accounts";
    }
}
