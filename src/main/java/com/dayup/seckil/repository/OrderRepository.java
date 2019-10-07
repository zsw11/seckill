package com.dayup.seckil.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dayup.seckil.model.Orders;

@Repository
public interface OrderRepository  extends JpaRepository<Orders, String>{

	public Orders findByUsernameAndCourseNo(String username, String courseNo);

	public List<Orders> findByUsername(String username, Sort sort);

}
