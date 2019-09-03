package com.emp.utils;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.emp.entity.User;
import com.emp.service.UserService;
 
/**
 *    
 *  认证(登录)
 *  授权
 *  的核心业务逻辑
 *
 */
public class MyRealm extends AuthorizingRealm {
 
	@Autowired
	private UserService userService;
 
	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		/**
		 * 注意principals.getPrimaryPrincipal()对应
		 * new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName())的第一个参数
		 */

		//获取当前身份 用户名
		String username = (String) principals.getPrimaryPrincipal();
		//授权验证对象 ,需要当前登录用户所有的角色和权限信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//从数据库中查找该用户有何角色和权限
		Set<String> roles = userService.queryRoles(username);
		Set<String> permissions = userService.queryPermissions(username);
		
		//为授权验证对象赋予对应角色和权限
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		//由info取判断用户是否有访问某个资源
		//或者做某种操作的权限
		return info;

	}
 
	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户名
		String username = (String) token.getPrincipal();

        // 通过username查询用户对象
        User user = userService.queryUser(username);
        //用户名存在则匹配密码
        if (user != null) {
             //1)principal：认证的实体信息，可以是userName，也可以是数据库表对应的用户的实体对象  
            Object principal = user.getUsername();

            //2)credentials：数据库中的密码  
            Object credentials = user.getPassword(); 

            //3)realmName：当前realm对象的name，调用父类的getName()方法即可  
            String realmName = getName();  

            //4)credentialsSalt盐值  
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());//使用用户名作为盐值  

            //根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
            //5)与数据库中用户名和密码进行比对，密码盐值加密，第4个参数传入realName。
            SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(principal, credentials,credentialsSalt,realmName);
            return authcInfo;
        } else {
        	//查询不到用户返回null
        	//认证失败
            return null;
        }

	}
 
}
