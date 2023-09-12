/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.services;

import com.ndt.repositories.ShippingProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingProcessService {
	@Autowired
	ShippingProcessRepository processRepository;

	public void deleteProcessByCartId(String cartId) {
	}	
}
