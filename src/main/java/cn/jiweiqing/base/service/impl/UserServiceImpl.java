package cn.jiweiqing.base.service.impl;

import cn.jiweiqing.base.bean.UserBean;
import cn.jiweiqing.base.mapper.UserMapper;
import cn.jiweiqing.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Weiqing Ji on 2019/11/8.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserBean loginIn(String username, String password){
        return userMapper.getInfo(username,password);
    }

    @Override
    public UserBean getInfoByUserName(String username) {
        return userMapper.getInfoByUserName(username);
    }
}
