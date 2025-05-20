package com.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.entities.Order;
import com.api.enums.CommunicationType;

@Service
public interface OrderService {

	public List<Order> getAllOrders();    
    public Order getOrderById(Integer id); 
	public String createOrder(Order order); 
	public String deleteOrder(Integer id);  
	public Order bookOrder(Integer userId, Integer astrologerId, CommunicationType type);
	public Order startChat(Integer orderId);
	public Order endChat(Integer orderId);
	
}
