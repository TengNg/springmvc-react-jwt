package com.ndt.services;

import com.ndt.repositories.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTransactionService {
	@Autowired
	PaymentTransactionRepository transactionRepository;

	public void removePaymentTransaction(String cartId) {
		this.transactionRepository.removePaymentTransactionByCartId(cartId);
	}
}
