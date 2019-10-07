package com.dayup.seckil.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dayup.seckil.base.controller.BaseApiController;
import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.model.Orders;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.IOrderService;

@RestController
public class OrderAPIController extends BaseApiController {

	
	@Autowired
	public IOrderService orderService;
	
	@RequestMapping(value="orderList",method=RequestMethod.GET)
	public Result<List<Orders>> orderList(User user){
		return Result.success(orderService.findAllByUsername(user.getUsername(),new Sort(Sort.Direction.DESC,"createDate")));
	}
}
