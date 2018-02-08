package com.zhongda.monitor.account.security;

import io.jsonwebtoken.SignatureException;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhongda.monitor.account.exception.NoStatelessTokenException;
import com.zhongda.monitor.account.model.Permission;
import com.zhongda.monitor.account.model.Role;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.PermissionService;
import com.zhongda.monitor.account.service.RoleService;
import com.zhongda.monitor.account.service.TokenService;
import com.zhongda.monitor.account.service.UserService;

/**
 * Title : StatelessRealm管理(无状态)
 * Description : 用户身份验证,授权 Realm 组件
 * @Author dengzm
 * @Date 2018年1月25日 下午8:29:37
 */
public class StatelessRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(StatelessRealm.class);
	
	@Resource
	private TokenService tokenService;
	 
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;
    
    @Override
    public boolean supports(AuthenticationToken token) {
        //表示此Realm只支持JwtToken类型
        return token instanceof StatelessToken;
    }

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());
        logger.error(username);
        final User user = userService.selectByUserName(username);
        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getUserId());
        for (Role role : roleInfos) {
            // 添加角色
        	logger.info(role.toString());
            authorizationInfo.addRole(role.getRoleSign());

            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getRoleId());
            for (Permission permission : permissions) {
                // 添加权限
            	logger.info(permission.toString());
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	StatelessToken statelessToken = (StatelessToken) token;
    	String userName = (String) statelessToken.getPrincipal();
    	if(null != userName && null != statelessToken.getCredentials()){
    		// 通过数据库进行验证
            final User user = userService.selectByUserName(userName);
            if (user == null) {
                throw new UnknownAccountException("该帐号不存在！");
            }else if("禁用".equals(user.getStatus())){
            	throw new DisabledAccountException("该账户已被禁用 ，请联系管理员！");
            }
            return  new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(userName), getName());
    	}
    	Map<String, Object> tokenBody = tokenService.parseTokenBody(statelessToken.getToken());
    	 
    	if (null == tokenBody) { 
    		throw new SignatureException("token令牌无效"); 
    	} 
    	if(null == tokenBody.get("userName")){
			throw new SignatureException("token令牌无效"); 
		}
		userName = (String) tokenBody.get("userName");
		// 通过数据库进行验证
        final User user = userService.selectByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("该帐号不存在！");
        }else if("禁用".equals(user.getStatus())){
        	throw new DisabledAccountException("该账户已被禁用 ，请联系管理员！");
        }
    	if (!tokenService.checkToken(statelessToken.getToken(), user.getPassword())) { 
    		throw new SignatureException("token令牌无效"); 
    	} 
        return  new SimpleAuthenticationInfo(userName, Boolean.TRUE, getName());
    }
    
    /**
     * 清除授权缓存
     */
    @Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

    /**
     *  清除认证缓存
     */
	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
