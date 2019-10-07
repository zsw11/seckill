package com.dayup.seckil.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.model.User;
import com.dayup.seckil.redis.UserRedis;
import com.dayup.seckil.repository.UserRpository;
import com.dayup.seckil.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	public UserRpository userRpository;
	
	@Autowired
	public UserRedis userRedis;
	
	@Override
	public User regist(User user) {
		return userRpository.saveAndFlush(user);
	}

	@Override
	public UserVO getUser(String username) {
		UserVO userVO = new UserVO();
		User user = userRedis.get("username");
		if(user == null){
			user = userRpository.findByUsername(username);
			if(user != null){
				userRedis.put(user.getUsername(), user, -1);
			}else{
				return null;
			}
		}
		BeanUtils.copyProperties(user, userVO);
		
		return userVO;
	}

	@Override
	public void saveUserToRedisByToken(UserVO dbUser, String token) {
		User user = new User();
		BeanUtils.copyProperties(dbUser, user);
		userRedis.put(token, user, 3600);
	}

	@Override
	public Object getUserFromRedisByToken(String token) {
		return userRedis.get(token);
	}

}
