package com.dayup.seckil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.model.User;
import com.dayup.seckil.redis.UserRedis;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	public UserService userService;
	
	@Autowired
	public UserRedis userRedis;
	
	
	@Test
	public void test() {
		User user = new User("andy", "123456");
		Assert.assertNotNull(userService.regist(user));
	}

	@Test
	public void testGetUser() {
		Assert.assertNotNull(userService.getUser("andy"));
	}
	
//	@Test
//	public void testPassword() {
//		UserVO user = userService.getUser("andy");
//		String password = MD5Util.inputToDb("123456", user.getDbflag());
//		Assert.assertEquals(password, user.getPassword());
//	}
	
	@Test
	public void testPutRedis() {
		User user = new User("andy","123456");
		userRedis.put(user.getUsername(), user, -1);
	}
}
