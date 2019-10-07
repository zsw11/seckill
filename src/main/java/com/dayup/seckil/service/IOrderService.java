package com.dayup.seckil.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.dayup.seckil.model.Orders;

public interface IOrderService {

	Orders getOrderByUsernameAndCourseNo(String username, String courseNo);

	Orders saveAndFlush(Orders orders);

	List<Orders> findAllByUsername(String username, Sort sort);

}
