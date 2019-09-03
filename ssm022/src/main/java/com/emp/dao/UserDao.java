package com.emp.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emp.entity.User;

public  interface UserDao {
     //依据用户名查找用户
	@Select("select id,username,password "
			+ "  from sh_user where username=#{username} ")
	User queryByUserName(@Param("username")String username);
	//注册
	//添加用户
	@Insert(" insert into sh_user(username,password) values (#{username},#{password})")
	void save(User user);
	
	 //依据用户名查询用户所有的角色
	//返回值类型是Shiro框架定义的
   @Select(" select r.rolename "
   		+ " from sh_user u inner join sh_user_role ur "
   		+ " on u.id = ur.user_id "
   		+ " inner join sh_role r "
   		+ " on ur.role_id = r.id "
   		+ " where u.username=#{username} ")
	Set<String> queryRoles(@Param("username")String username);
	 //依据用户名查询用户所有的权限
    @Select(" select distinct p.permission_name "
    		+ " from sh_user u inner join sh_user_role ur "
    		+ " on u.id = ur.user_id "
    		+ " inner join sh_role r "
    		+ " on ur.role_id = r.id "
    		+ " inner join sh_role_permission rp "
    		+ " on r.id = rp.role_id "
    		+ " inner join sh_permission p "
    		+ " on rp.permission_id = p.id "
    		+ " where u.username=#{username} ")
	Set<String> queryPermissions(@Param("username")String username);
	
	
	
}
