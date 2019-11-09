package cn.jiweiqing.base.mapper;

import cn.jiweiqing.base.bean.UserBean;

/**
 * Created by Weiqing Ji on 2019/11/8.
 */
public interface UserMapper {
    UserBean getInfo(String username, String password);
    UserBean getInfoByUserName(String username);
}
