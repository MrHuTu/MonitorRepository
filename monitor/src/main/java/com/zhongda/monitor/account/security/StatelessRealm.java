package com.zhongda.monitor.account.security;

import io.jsonwebtoken.SignatureException;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.account.model.Permission;
import com.zhongda.monitor.account.model.Role;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.PermissionService;
import com.zhongda.monitor.account.service.RoleService;
import com.zhongda.monitor.account.service.TokenService;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.account.utils.TokenUtils;
import com.zhongda.monitor.core.utils.SpringUtils;

/**
 * Title : StatelessRealm管理(无状态)
 * Description : 用户身份验证,授权 Realm 组件
 * @Author dengzm
 * @Date 2018年1月25日 下午8:29:37
 */
@Component("statelessRealm")
public class StatelessRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(StatelessRealm.class);
	
	private TokenService tokenService;
	
    private UserService userService;
    
    private RoleService roleService;
	
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
    	roleService = SpringUtils.getBean(RoleService.class);
    	permissionService = SpringUtils.getBean(PermissionService.class);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
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
    	tokenService = SpringUtils.getBean(TokenService.class);
    	userService = SpringUtils.getBean(UserService.class);
    	StatelessToken statelessToken = (StatelessToken) token;
    	User user = TokenUtils.getUserFromeToken(statelessToken.getToken());
    	if (null == user || null == user.getUserName()) { 
    		throw new SignatureException("token令牌失效，请重新申请！"); 
    	} 
		// 通过数据库进行验证
        user = userService.selectByUserName(user.getUserName());
        if (null == user || "禁用".equals(user.getStatus())) {
            throw new SignatureException("token令牌失效，请重新申请！");
        }
    	if (!tokenService.checkToken(statelessToken.getToken(), user.getPassword())) { 
    		throw new SignatureException("token令牌失效，请重新申请！"); 
    	} 
        return  new SimpleAuthenticationInfo(user, statelessToken.getToken(), getName());
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
