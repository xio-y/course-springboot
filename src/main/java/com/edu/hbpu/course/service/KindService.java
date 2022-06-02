package com.edu.hbpu.course.service;

import com.edu.hbpu.course.entity.Kind;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface KindService extends IService<Kind> {
    List<Kind> getNumsByKind();
}
