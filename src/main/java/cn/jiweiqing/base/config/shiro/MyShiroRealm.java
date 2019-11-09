package cn.jiweiqing.base.config.shiro;

import cn.jiweiqing.base.bean.UserBean;
import cn.jiweiqing.base.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

/**
 * @author shiro验证实现类
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName = (String) token.getPrincipal();
        UserBean user = userService.getInfoByUserName(loginName);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "");
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
//    @Autowired
//    private IPassportService iPassportService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    /*@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        String passportName = (String) authcToken.getPrincipal();
        Session session = SecurityUtils.getSubject().getSession();
        return new SimpleAuthenticationInfo("sanbao", "123456", "");
    }*/


    /**
     * 授权
     */
   /* @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String passportName = principals.getPrimaryPrincipal().toString();
//        Set<String> set = RedisUtil.getSetValue(redisTemplate, USER_PERMISSION.concat(passportName));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addStringPermissions(set);
        return authorizationInfo;
    }*/

}
