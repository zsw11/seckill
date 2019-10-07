package com.dayup.seckil.service;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.model.User;

public interface UserService {

	public User regist(User user);

	public UserVO getUser(String username);

	public void saveUserToRedisByToken(UserVO dbUser, String token);

	public Object getUserFromRedisByToken(String token);
}
