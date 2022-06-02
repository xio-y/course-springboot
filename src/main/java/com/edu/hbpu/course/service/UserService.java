package com.edu.hbpu.course.service;

import com.edu.hbpu.course.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    void disable(User u);
}
