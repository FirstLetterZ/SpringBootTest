package com.zpf.demo.user.config;

import com.auth0.jwt.interfaces.Claim;
import com.zpf.demo.global.AccountState;
import com.zpf.demo.global.Constants;
import com.zpf.demo.user.entity.UserBaseEntity;
import com.zpf.demo.user.entity.UserRoleEntity;
import com.zpf.demo.user.service.UserBaseService;
import com.zpf.demo.user.service.UserRoleService;
import com.zpf.demo.user.util.TokenManager;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private UserRoleService userRoleService;
    private SimpleAuthorizationInfo authInfoNoLogin;
    private SimpleAuthenticationInfo tokenAuthNoLogin;

    //默认只支持UsernamePasswordToken，所以必须改变返回值
    @Override
    public boolean supports(AuthenticationToken token) {
        return true;
    }

    @Override
    protected void onInit() {
        super.onInit();
        //初始化游客信息
        authInfoNoLogin = new SimpleAuthorizationInfo();
        authInfoNoLogin.addRole("anon");
        authInfoNoLogin.addStringPermission("anon");
        SimplePrincipalCollection spcNoLogin = new SimplePrincipalCollection(Constants.TOKEN_TOURIST, this.getName());
        UserBaseEntity userNoLogin = new UserBaseEntity();
        userNoLogin.setRoleName("anon");
        spcNoLogin.add(userNoLogin, this.getName());
        tokenAuthNoLogin = new SimpleAuthenticationInfo(spcNoLogin, Constants.TOKEN_TOURIST);
    }

    //获取权限
    //参数PrincipalCollection来源于doGetAuthenticationInfo返回的AuthenticationInfo,本demo内使用的是SimpleAuthenticationInfo，
    //所以对应其构造函数的第一个值
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == tokenAuthNoLogin.getPrincipals()) {
            return authInfoNoLogin;
        }
        UserBaseEntity userBase = principalCollection.oneByType(UserBaseEntity.class);
        if (userBase == null) {
            return authInfoNoLogin;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        UserRoleEntity roleEntity = userRoleService.findRolePermission(userBase.getRoleName());
        if (roleEntity == null || !roleEntity.checkLegal()) {
            info.addRole("anon");
            info.addStringPermission("anon");
        } else {
            info.addRole(roleEntity.getRoleName());
            List<String> pList = roleEntity.getPermissions();
            if (pList == null || pList.size() == 0) {
                info.setRoles(new HashSet<>());
            } else {
                info.setRoles(new HashSet<>(pList));
            }
        }
        return info;
    }

    //认证token
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tokenString = null;
        if (authenticationToken != null && authenticationToken.getPrincipal() != null) {
            tokenString = authenticationToken.getPrincipal().toString();
        }
        if (tokenString == null || tokenString.length() == 0 || tokenString.equals(Constants.TOKEN_TOURIST)) {//游客身份
            return tokenAuthNoLogin;
        }
        SimplePrincipalCollection spc = new SimplePrincipalCollection();
        spc.add(tokenString, this.getName());
        Map<String, Claim> tokenInfo = TokenManager.decodeTokenInfo(tokenString);
        if (tokenInfo == null) {
            doClearCache(spc);
            throw new AuthenticationException("token认证失败");
        }
        String userId = null;
        try {
            userId = tokenInfo.get(Constants.USER_ID).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userId == null) {
            doClearCache(spc);
            throw new AuthenticationException("无效的token");
        }
        UserBaseEntity userBase = userBaseService.findUserBaseInfoById(userId);
        if (userBase == null) {
            doClearCache(spc);
            throw new AuthenticationException("该用户不存在！");
        }
        if (userBase.getAccountState() != AccountState.NORMAL.getCode()) {
            doClearCache(spc);
            throw new AuthenticationException("账号状态异常");
        }
        spc.add(userBase, this.getName());
        return new SimpleAuthenticationInfo(spc, tokenString);
    }

    //修改用户认证缓存key
    //这个方法在删除的时候会用,
    @Override
    protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
        String token = principals.oneByType(String.class);
        return token == null ? super.getAuthorizationCacheKey(principals) : token;
    }

    //这个方法在缓存的时候会用
    @Override
    protected Object getAuthenticationCacheKey(AuthenticationToken token) {
        String tokenString = null;
        if (token != null && token.getPrincipal() != null) {
            tokenString = token.getPrincipal().toString();
        }
        return tokenString == null ? "" : tokenString;
    }

    //修改用户权限缓存key
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        String token = principals.oneByType(String.class);
        return token == null ? super.getAuthorizationCacheKey(principals) : token;
    }
}
