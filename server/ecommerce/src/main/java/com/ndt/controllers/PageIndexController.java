/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.controllers;

import com.ndt.services.CategoryService;
import com.ndt.services.ProductService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class PageIndexController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService cateService;
    @Autowired
    private Environment env;
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("categories", this.cateService.getCategories());
    }
    
    @RequestMapping("/")
	public String index(Model model) {
        model.addAttribute("products", this.productService.getProducts());
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.productService.getProducts().size();
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        return "index";
    }
}