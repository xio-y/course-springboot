package com.edu.hbpu.course.serviceImpl;

import com.edu.hbpu.course.entity.User;
import com.edu.hbpu.course.mapper.UserMapper;
import com.edu.hbpu.course.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void disable(User u){
        userMapper.disable(u);
    }
}
