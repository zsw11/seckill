package com.dayup.seckil.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dayup.seckil.model.Orders;
import com.dayup.seckil.repository.OrderRepository;
import com.dayup.seckil.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	public Orders getOrderByUsernameAndCourseNo(String username, String courseNo) {
		return orderRepository.findByUsernameAndCourseNo(username, courseNo);
	}

	@Override
	public Orders saveAndFlush(Orders orders) {
		return orderRepository.saveAndFlush(orders);
	}

	@Override
	public List<Orders> findAllByUsername(String username, Sort sort) {
		return orderRepository.findByUsername(username, sort);
	}

}
