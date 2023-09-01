package com.ndt.services;

import com.ndt.pojo.*;
import com.ndt.repositories.TestRepository;
import com.ndt.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepo;

    public List<SaleOrder> userSaleOrders(int id) {
		return this.testRepo.getUserOrders(id);
    }

	public List<OrderDetail> orderDetails(int orderId) {
		return this.testRepo.getOrderDetails(orderId);
	}
}
