package com.lin.rbacshiro.shiro;

import com.lin.rbacshiro.dto.Role;
import com.lin.rbacshiro.dto.User;
import com.lin.rbacshiro.service.IUserService;
import com.lin.rbacshiro.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        String id = JwtUtil.decode(token);  // 解密Token
        if (id == null) {
            // 解密失败，抛出异常
            throw new AuthenticationException("Invalid token.");
        }
        // 解密成功，返回SimpleAuthenticationInfo对象
        return new SimpleAuthenticationInfo(token, token, "myRealm");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String id = JwtUtil.decode(principals.toString());
        User user = userService.getUserById(id);

        if (user != null) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            // 获取当前用户的所有角色，并且通过addRole添加到simpleAuthorizationInfo当中
            // 这样当Shiro内部检查用户是否有某项权限时就会从SimpleAuthorizationInfo中拿取校验
            List<Role> roles = userService.listRoleByUserId(user.getId());
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getName());
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }
}
