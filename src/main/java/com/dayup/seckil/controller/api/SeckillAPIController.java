package com.dayup.seckil.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dayup.seckil.base.controller.BaseApiController;
import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.model.Orders;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.ISeckillService;

@RestController
public class SeckillAPIController extends BaseApiController implements InitializingBean{

	@Autowired
	public ISeckillService seckillService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		seckillService.cacheAllCourse();
	}
	
	@RequestMapping(value="{path}/seckill/{courseNo}",method=RequestMethod.GET)
	public Result<Orders> seckill(User user, @PathVariable String courseNo, @PathVariable String path, HttpServletRequest request){
		if(user == null){
			return Result.failure();
		}
		System.out.println("========================");
		return seckillService.seckillFlow(user, courseNo, path, request);
	}
	
	@RequestMapping(value="seckill/{courseNo}",method=RequestMethod.GET)
	public Result<Orders> seckill(User user, @PathVariable String courseNo){
		if(user == null){
			return Result.failure();
		}
		return seckillService.seckillFlow(user, courseNo);
	}

	@RequestMapping(value="seckillResult/{courseNo}",method=RequestMethod.GET)
	public Result<Orders> seckillResult(User user, @PathVariable String courseNo){
		if(user == null){
			return Result.failure();
		}
		return seckillService.seckillResult(user, courseNo);
	}

	@RequestMapping(value="getPath/{courseNo}",method=RequestMethod.GET)
	public String getPath(User user, @PathVariable String courseNo){
		if(user == null){
			
		}
		return seckillService.getPath(user, courseNo);
	}
}
