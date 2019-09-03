package com.emp.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.emp.dao.UserDao;
import com.emp.entity.User;
import com.emp.service.UserService;

@Service
public class UserServiceImpl
  implements UserService{
   //注入Dao对象
	@Resource
	private UserDao userDao;
	
	@Override
	public User queryUser(String username) {
		   User user = userDao.queryByUserName(username);
		return user;
	}

	@Override
	public Set<String> queryRoles(String username) {
		  Set<String> roles = userDao.queryRoles(username);
		return roles;
	}

	@Override
	public Set<String> queryPermissions(String username) {
		Set<String> pers = userDao.queryPermissions(username);
		return pers;
	}

	@Override
	public void addUser(User user) {
           //加盐，加密的效果
		   //MD5  "123"+"/asd"-->XXOOdasds434-->X))**&&-->...-->....
		 //加密密码
        String password  // 算法   需要加密的密码    盐    加密的次数
        = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 1024).toString();
        // password  就是加密后的密码
        // 用户加密后的密码置换原来从页面传来的密码
        user.setPassword(password);
        //将user保存到数据库中
        userDao.save(user);
	}

}
