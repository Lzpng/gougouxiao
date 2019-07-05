package com.gougouxiao.shop.realm;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.pojo.Seller;
import com.gougouxiao.service.SellerService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义认证域
 */
public class SellerAuthorizingRealm extends AuthorizingRealm {

    @Reference(timeout = 10000)
    private SellerService sellerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录名
        String username = authenticationToken.getPrincipal().toString();
        System.out.println("username = " + username);
        //根据用户查询商家对象
        Seller seller = sellerService.findOne(username);
        // 判断seller 是否为空，审核状态码是否为：1
        if (seller != null && "1".equals(seller.getStatus())) {
            // 返回身份认证信息封装对象
            return new SimpleAuthenticationInfo(username,
                    seller.getPassword(), //数据库的密码
                    ByteSource.Util.bytes(seller.getSellerId()),
                    this.getName());
        }
        return null;

    }
}
