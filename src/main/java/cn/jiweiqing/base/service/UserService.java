package cn.jiweiqing.base.service;

import cn.jiweiqing.base.bean.UserBean;

/**
 * Created by Weiqing Ji on 2019/11/8.
 */
public interface UserService {
    UserBean loginIn(String username, String password);

    UserBean getInfoByUserName(String username);
}
